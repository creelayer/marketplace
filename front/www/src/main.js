import { createApp } from 'vue'
import App from './App.vue'
import Router from './router/Index'
import "bootstrap/dist/css/bootstrap.min.css"
import "./assets/css/main.css"
import  "bootstrap"

createApp(App).use(Router).mount('#app')
