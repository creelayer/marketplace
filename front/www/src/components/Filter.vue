<template>
  <form>
    <div class="filter-item" v-for="(group,key) in filter" :key="key">
      <div class="label">{{ key }}</div>
      <ul>
        <li v-for="item in group" :key="item.id">
          <input class="regular-checkbox" type="checkbox" v-bind:id="'f'+item.id" v-bind:value="item.id"
                 v-model="checked"
                 v-on:change="onFilterClick" :disabled="item.count === 0">
          <label v-bind:for="'f'+item.id" v-bind:style="item.count === 0 ? 'opacity:0.6':''"><span/>{{ item.name }}
            ({{ item.count }})</label>
        </li>
      </ul>
    </div>
  </form>
</template>

<script>

import Http from "@/js/Http";

export default {
  name: 'Filter',
  props: {
    alias: null
  },
  watch: {
    $route() {
      this.updateFilter()
    }
  },
  data() {
    return {
      filter: [],
      checked: [],
    }
  },
  created() {
    this.fetchFilter();
    if (this.$route.query.q) {
      this.updateFilter();
      this.fetchParams();
    }
  },
  methods: {
    updateFilter: function () {
      Http.get(this._getUrl('/v1/filter/' + this.alias, this.$route.query))
          .then(res => {
            this._update(res.content);
          });
    },
    fetchFilter: function () {
      Http.get(this._getUrl('/v1/filter/' + this.alias))
          .then(res => {
            this.filter = res.content.reduce(function (rv, x) {
              (rv[x['group']] = rv[x['group']] || []).push(x);
              return rv;
            }, {});
          });
    },
    fetchParams: function () {
      this.checked = this.$route.query.q ? this.$route.query.q.split('-').map(v => parseInt(v)) : [];
    },
    onFilterClick: function () {
      let request = {name: 'catalog-filter', alias: this.alias};
      if (this.checked.length) request.query = {q: this.checked.sort().join('-')};
      this.$router.push(request);
    },
    _getUrl: function (link, query = {}) {
      let url = new URL(window.location.protocol + "//" + window.location.host + link);
      Object.keys(query).forEach(key => {
        if (key === "q") {
          url.searchParams.append("tags", this.$route.query.q.replaceAll("-", ','))
        } else {
          url.searchParams.append(key, this.$route.query[key])
        }
      });
      return url;
    },
    _update: function (content) {
      for (let group in this.filter) {
        for (let i in this.filter[group]) {
          this.filter[group][i].count = 0;
        }
      }
      for (let group in this.filter) {
        for (let i in this.filter[group]) {
          let item = this.filter[group][i];
          for (let v of content) {
            if (item.id === v.id) {
              this.filter[group][i].count = v.count;
            }
          }
        }
      }
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.filter-item {
  box-shadow: 0 0 0 1px #ededed;
  background-color: #fff;
  padding: 15px;
}

.filter-item .label {
  color: #3b6d9a;
  padding-left: 8px;
  margin-bottom: 10px;
}

ul {
  list-style: none;
  padding: 0;
}

ul li {
  border-radius: 5px;
}

ul li:hover {
  background-color: rgba(63, 78, 93, 0.05);
}

.regular-checkbox + label{
  width: 100%;
}
.regular-checkbox + label span {
  margin: 8px;
}

</style>
