<template>
  <div>
    <ul>
      <li v-for="item in categories" :key="item.id">
        <router-link v-bind:to="'/c/'+item.url">{{ item.name }}</router-link>
      </li>
    </ul>
  </div>
</template>

<script>

import Http from "@/js/Http";

export default {
  name: 'Category',
  data() {
    return {
      categories: [],
    }
  },
  created() {
    this.fetchCategories()
  },
  methods: {
    fetchCategories: function () {
      Http.get('/v1/category')
          .then(res => {
            this.categories = res.content;
          });
    },
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
