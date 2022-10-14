import Vue from "vue"
import Router from "vue-router"

import Main from "./components/Main"
import Cos from "./components/Cos"
import Produse from "./components/Produse"
import ProduseClient from "./components/ProduseClient"
Vue.use(Router)
const router = new Router({
    mode: "history",
    routes: [
        { path: "/", name: Main, component: Main },
        { path: "/Cos", name: Cos, component: Cos },
        { path: "/Produse", name: Produse, component: Produse },
        { path: "/client", name: ProduseClient, component: ProduseClient }
    ]
})
export default router