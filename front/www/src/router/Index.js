import {createWebHistory, createRouter} from "vue-router";
import Index from "./../views/Index"
import Catalog from "./../views/Catalog"
import Product from "@/views/Product";

const routes = [
    {
        path: "/",
        name: "Index",
        component:  Index
    },
    {
        path: "/catalog",
        name: "catalog",
        component:  Catalog
    },
    {
        path: "/c/:alias",
        name: "catalog-filter",
        component:  Catalog
    },
    {
        path: "/p/:alias",
        name: "product",
        component:  Product
    }
];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router