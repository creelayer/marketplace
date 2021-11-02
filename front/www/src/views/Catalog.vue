<template>
  <div class="container-fluid">
    <h1>Catalog</h1>
    <div class="row">
      <div class="col-3">
        <Category ref="category" v-if="!alias"/>
        <Filter ref="filter" v-if="alias" :alias="alias"/>
      </div>
      <div class="col-9">

        <div class="row">
          <div class="col-3" v-for="item in products" :key="item.id">
            <router-link v-bind:to="'/p/'+item.url">{{ item.name }}</router-link>
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
      Http.get(this.getUrl('/v1/product/search' + (this.alias ? '/' + this.alias : '')))
          .then(res => {
            this.products = res.content;
            this.currentPage = res.number ? res.number : 0;
            this.totalPages = res.totalPages;
          });
    },
    getUrl: function (link, additionalQueryParams = {}) {
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
  }
}
</script>

<style scoped>

</style>