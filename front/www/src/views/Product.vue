<template>
  <div class="container-xxl">
    <h1 v-if="product">{{ product.name }}</h1>
    <img v-bind:src="preview(product)" class="img-preview">
  </div>
</template>

<script>
import Http from "@/js/Http";
import Config from '@/main.config';

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
    preview: function (product) {
      return Config.IMAGE.DOMAIN + '/files/' + Config.IMAGE.PRODUCT_PREVIEW + '/' + product.image
    }
  }
}
</script>

<style scoped>

.img-preview{
  max-width: 350px;
}

</style>