<template>
  <div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel" data-bs-interval="false">
    <div class="carousel-indicators">
      <div type="button" data-bs-target="#carouselExampleIndicators" v-for="(preview, index) in previews"
           :key="preview" v-bind:data-bs-slide-to="index" v-bind:class="'indicator '+(index ? '':'active')">
        <img v-bind:src="'http://localhost:5442/files/180x180/'+preview" class="d-block" alt="...">
      </div>
    </div>
    <div class="carousel-inner">
      <div v-for="(preview, index) in previews" :key="preview" v-bind:class="'carousel-item '+(index ? '':'active')">
        <img v-bind:src="'http://localhost:5442/files/800x800/'+preview" class="d-block" alt="...">
      </div>
    </div>
  </div>
</template>

<script>
import Config from "@/main.config";

export default {
  name: "Preview",
  props: {
    previews: []
  },
  methods: {
    url: function (item) {
      return Config.IMAGE.DOMAIN + '/files/' + Config.IMAGE.PRODUCT_PREVIEW + '/' + item;
    },
  }
}
</script>

<style scoped>
.carousel {
  /*max-width: 400px;*/
}

.carousel-inner {
  /*margin-left: 70px;*/
  /*justify-content: center;*/
}

.carousel-item img {
  width: 410px;
  margin: 0 auto;
}

.carousel-indicators {
  right: auto;
  left: 0;
  bottom: auto;
  margin-left: 0;
  margin-right: 0;
  display: block;
}

.carousel-indicators [data-bs-target] {
  border-radius: 10px;
  border: 1px solid #fff;
  margin: 10px 0;
  flex: none;
  width: 48px;
  height: 48px;
  opacity: 1;
}

.carousel-inner .carousel-item {
  transition: -webkit-transform .3s ease;
  transition: transform .3s ease;
  transition: transform .3s ease, -webkit-transform .3s ease;
}

.carousel-indicators .indicator.active {
  border: 1px solid #ff8637;
}

.indicator {
  height: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.indicator img {
  max-width: 48px;
  max-height: 38px;
  margin: 0 auto;
}

</style>