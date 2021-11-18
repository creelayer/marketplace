<template>
  <div class="bg container-xxl" v-if="product">
    <div class="m-4 row">
      <div class="col-6">
        <preview :previews="previews"></preview>
      </div>
      <div class="col-6">
        <h1>{{ product.name }}</h1>

        <span class="available green mt-2">
            <svg viewBox="0 0 24 24" fill="currentColor" class=""><path
                fill-rule="evenodd" clip-rule="evenodd"
                d="M12 2C6.48 2 2 6.48 2 12C2 17.52 6.48 22 12 22C17.52 22 22 17.52 22 12C22 6.48 17.52 2 12 2ZM10 14.17L15.88 8.29C16.27 7.9 16.91 7.9 17.3 8.29C17.69 8.68 17.69 9.31 17.3 9.7L10.71 16.29C10.32 16.68 9.69 16.68 9.3 16.29L6.71 13.7C6.32 13.31 6.32 12.68 6.71 12.29C7.1 11.9 7.73 11.9 8.12 12.29L10 14.17Z"></path>
          </svg>Є в наявності
          </span>

        <div class="prices mt-2">
          <span class="price-block" v-if="product.priceMin && product.priceMax && product.priceMin < product.priceMax">
             <span class="price min">від <span class="p">{{ priceFormat(product.priceMin) }}</span>  <span
                 class="currency">₴</span></span>
          <span class="price max">до <span class="p">{{ priceFormat(product.priceMax) }}</span> <span
              class="currency">₴</span></span>
          </span>
          <span class="price-block" v-else>
             <span class="price min">ціна <span class="p">{{ priceFormat(product.priceMin) }}</span> <span
                 class="currency">₴</span></span>
          </span>


          <button class="bb mt-3">
            <svg viewBox="0 0 24 24" fill="currentColor" class="icon-0-2-183 mr8 icon-0-2-26"><path fill-rule="evenodd" clip-rule="evenodd" d="M10.5547 2.16806C11.0142 2.47441 11.1384 3.09528 10.8321 3.55481L7.20184 9.00012H16.7982L13.168 3.55481C12.8616 3.09528 12.9858 2.47441 13.4453 2.16806C13.9048 1.8617 14.5257 1.98588 14.8321 2.44541L19.2019 9.00014H22C22.55 9.00014 23 9.45014 23 10.0001L22.97 10.2701L20.43 19.5401C20.19 20.3801 19.42 21.0001 18.5 21.0001H5.5C4.58 21.0001 3.81 20.3801 3.58 19.5401L1.04 10.2701C1.01 10.1801 1 10.0901 1 10.0001C1 9.45014 1.45 9.00014 2 9.00014H4.79813L9.16795 2.44541C9.4743 1.98588 10.0952 1.8617 10.5547 2.16806ZM18.5 19.0001L5.51 19.0101L3.31 11.0001H20.7L18.5 19.0001Z"></path></svg><span>Купити</span>
          </button>

        </div>

      </div>
    </div>
  </div>
</template>

<script>
import Http from "@/js/Http";
import Preview from "@/components/Preview";

export default {
  name: "Product",
  components: {
    Preview
  },
  data() {
    return {
      alias: null,
      product: null,
      index: null,
      previews: []
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
            this.previews = this.product.preview ? this.product.preview : [];
          });
    },
    priceFormat: function (number) {
      return (number / 100).toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ");
    }
  }
}
</script>

<style scoped>

.bg {
  border-radius: 5px;
  background-color: #fff;
  box-shadow: 0 0 0 1px #ededed;
  margin-top: 10px;
  overflow: hidden;
}

.img-preview {
  max-width: 350px;
}

.available {
  color: rgba(0, 0, 0, 0.54);
  height: 22px;
  display: inline-flex;
  padding: 0 8px 0 4px;
  /*line-height: 16px;*/
  border-radius: 20px;
  background-color: #F6F7F7;

}

.available.green {
  color: #57ae00;
  background-color: #F1F8EB;
}

.available svg {
  margin-right: 6px;
}

.prices {

}

.price {
  font-size: 16px;
}

.price-block{
  display: block;
}

.price.max {
  margin-left: 20px;
}

.price .p {
  font-size: 32px;
  line-height: 42px;
}

.price .currency {
  font-size: 22px;
}

.bb{
  width: 340px;
  height: 46px;
  font-size: 18px;
  color: #fff;
  border: 1px solid #ff680a;
  background: #ff680a;
  border-radius: 10px;
}

.bb span{
  margin-left: 10px;
  text-transform: uppercase;
  position: relative;
}

.bb > svg {
  width: 24px;
  height: 24px;
  position: relative;
  margin-top: -4px;
}

</style>