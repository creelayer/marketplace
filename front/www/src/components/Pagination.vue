<template>
  <div aria-label="Page navigation " :key="$route.fullPath">
    <ul class="pagination mt-5 mb-5">
      <li class="page-item" v-if="currentPage > 0">
        <router-link v-bind:to="urlTo(currentPage-1)" aria-label="Previous" class="page-link">
          <span aria-hidden="true">&laquo;</span>
        </router-link>
      </li>
      <li class="page-item disabled" v-else>
        <span aria-hidden="true" class="page-link">&laquo;</span>
      </li>
      <li class="page-item" v-for="n in pages()" :key="n" v-bind:class="{ active: n === currentPage }">
        <router-link v-bind:to="urlTo(n)" v-if="n !== currentPage" class="page-link">{{ n + 1 }}</router-link>
        <span class="page-link" v-else>{{ n + 1 }}</span>
      </li>
      <li class="page-item" v-if="currentPage < totalPages">
        <router-link v-bind:to="urlTo(currentPage+1)" aria-label="Next" class="page-link"><span
            aria-hidden="true">&raquo;</span></router-link>
      </li>
      <li class="page-item disabled" v-else>
        <span aria-hidden="true" class="page-link">&raquo;</span>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "Pagination",
  props: {
    currentPage: {
      type: Number,
      default: 0
    },
    totalPages: {
      type: Number,
      required: true
    },
    pagerLen: {
      type: Number,
      default: 10
    },
  },
  methods: {
    pages: function () {
      let pager = [];
      let start = Math.max(this.currentPage - 5, 0);
      let end = Math.min(start + this.pagerLen, this.totalPages);

      for (let i = start; i < end; i++) {
        pager.push(i);
      }

      return pager;
    },
    urlTo: function (page) {
      return this._addOrUpdateUrlParam("page", page);
    },
    _addOrUpdateUrlParam: function (name, value) {
      let href = window.location.pathname+ window.location.search;
      let regex = new RegExp("[&\\?]" + name + "=");
      if (regex.test(href)) {
        regex = new RegExp("([&\\?])" + name + "=\\d+");
        return href.replace(regex, "$1" + name + "=" + value);
      } else {
        if (href.indexOf("?") > -1)
          return href + "&" + name + "=" + value;
        else
          return href + "?" + name + "=" + value;
      }
    }
  }
}
</script>

<style scoped>

</style>