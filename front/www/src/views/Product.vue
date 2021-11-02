<template>
  <h1 v-if="product">{{product.name}}</h1>
</template>

<script>
import Http from "@/js/Http";

export default {
  name: "Product",
  data() {
    return {
      alias: null,
      product: null
    }
  },
  created() {
    this.fetchProduct();
  },
  methods: {
    fetchProduct: function () {
      this.alias = this.$route.params.alias
      Http.get('/v1/product/' + this.alias)
          .then(res => {
            this.product = res.content;
          });
    },
  }
}
</script>

<style scoped>

</style>