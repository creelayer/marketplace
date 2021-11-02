<template>
  <div aria-label="Page navigation ">
    <ul class="pagination mt-5 mb-5">
      <li class="page-item" v-if="currentPage > 0">
        <router-link v-bind:to="'/catalog?page='+(currentPage-1)" aria-label="Previous" class="page-link">
          <span aria-hidden="true">&laquo;</span>
        </router-link>
      </li>
      <li class="page-item disabled" v-else>
        <span aria-hidden="true" class="page-link">&laquo;</span>
      </li>
      <li class="page-item" v-for="n in pages()" :key="n" v-bind:class="{ active: n === currentPage }">
        <router-link v-bind:to="'/catalog?page='+n" v-if="n !== currentPage" class="page-link">{{ n+1 }}</router-link>
        <span class="page-link" v-else>{{ n+1 }}</span>
      </li>
      <li class="page-item" v-if="currentPage < totalPages">
        <router-link v-bind:to="'/catalog?page='+(currentPage+1)" aria-label="Next" class="page-link"><span aria-hidden="true">&raquo;</span></router-link>
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
    }
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
    }
  }
}
</script>

<style scoped>

</style>