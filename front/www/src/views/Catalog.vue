<template>
  <div class="container-xxl">
    <h1>Catalog</h1>

    <div class="row">
      <div class="col-3">
        <Category ref="category" v-if="!alias"/>
        <Filter ref="filter" v-if="alias" :alias="alias"/>
      </div>
      <div class="col-9">

        <div class="row">
          <div class="col-3 item" v-for="(groups,gid) in products" :key="gid">
            <div v-for="product in groups" :key="product.id">
              <router-link v-bind:to="'/p/'+product.url"><img v-bind:src="preview(product)" class="img-preview">
              </router-link>
              <router-link v-bind:to="'/p/'+product.url" class="d-block mt-4">{{ product.name }}</router-link>

              <div class="short-case mt-3">
                <div class="title">Характеристики</div>
                <ul>
                  <li v-for="short in product.shortCase" :key="short.label">
                    <span>{{ short.label }}</span> {{ short.value }}
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>

        <Pagination v-if="totalPages"
                    :current-page="currentPage"
                    :totalPages="totalPages"
        />

      </div>
    </div>


  </div>
</template>


<script>

import Category from "@/components/Category";
import Pagination from "@/components/Pagination";
import Http from "@/js/Http";
import Filter from "@/components/Filter";
import Config from '@/main.config';

export default {
  name: "Catalog",
  components: {Filter, Category, Pagination},
  data() {
    return {
      alias: null,
      products: [],
      currentPage: 1,
      totalPages: null
    }
  },
  watch: {
    $route() {
      this.fetchProducts();
    }
  },
  created() {
    this.fetchProducts()
  },
  methods: {
    fetchProducts: function () {
      this.alias = this.$route.params.alias;

      if (this.alias == null) {
        return;
      }

      Http.get(this._getUrl('/v1/product/search/' + this.alias))
          .then(res => {
            this.products = this._groupBy(res.content, "gid");
            this.currentPage = res.number ? res.number : 0;
            this.totalPages = res.totalPages;
          });
    },
    preview: function (product) {
      if (product.preview === null || product.preview.length === 0) {
        return 'default image';
      }
      return Config.IMAGE.DOMAIN + '/files/' + Config.IMAGE.CATALOG_PREVIEW + '/' + product.preview[0];
    },
    _getUrl: function (link, additionalQueryParams = {}) {
      let url = new URL(window.location.protocol + "//" + window.location.host + link);
      Object.keys(this.$route.query).forEach(key => {

        if (key === "q") {
          url.searchParams.append("tags", this.$route.query.q.replaceAll("-", ','))
        } else {
          url.searchParams.append(key, this.$route.query[key])
        }

      });
      Object.keys(additionalQueryParams).forEach(key => url.searchParams.append(key, additionalQueryParams[key]));
      return url;
    },
    _groupBy: function (xs, key) {
      return xs.reduce(function (rv, x) {
        (rv[x[key]] = rv[x[key]] || []).push(x);
        return rv;
      }, {});
    }
  }
}
</script>

<style scoped>

.img-preview {
  display: block;
  max-width: 180px;
  margin: 0px auto;
}

.item {
  box-shadow: 0 0 0 1px #ededed;
  background-color: #fff;
  padding: 2.8rem 1.4rem 1.2rem;
}

.short-case .title {
  font-weight: bold;
  color: #000;
  font-size: 13px;
  margin-bottom: 10px;
}

.short-case ul {
  list-style: none;
  padding-left: 10px;
}

.short-case ul li::before {
  content: "\2022"; /* Add content: \2022 is the CSS Code/unicode for a bullet */
  color: red; /* Change the color */
  font-weight: bold; /* If you want it to be bold */
  display: inline-block; /* Needed to add space between the bullet and the text */
  width: 1em; /* Also needed for space (tweak if needed) */
  margin-left: -1em; /* Also needed for space (tweak if needed) */
}

.short-case li {
  font-size: 12px;
}

.short-case span {
  color: #99a1a7;
}

</style>