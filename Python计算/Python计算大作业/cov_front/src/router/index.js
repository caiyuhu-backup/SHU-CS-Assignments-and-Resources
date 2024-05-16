import Vue from 'vue'
import VueRouter from 'vue-router'
import SellerPage from '@/views/HelpPage'
import TrendPage from '@/views/TrendPage'
import MapPage from '@/views/MapPage'
import RankPage from '@/views/RankPage'
import HotPage from '@/views/HotPage'
import StockPage from '@/views/StockPage'
import HelpPage from '@/views/HelpPage'
import shanghaiTrend from '@/components/ShanghaiTrend'
import shanghaiTrendPage from '@/views/ShanghaiTrendPage'

Vue.use(VueRouter)

const routes = [
  {
    path: '/HelpPage',
    component: HelpPage,
  },
  {
    path: '/trendpage',
    component: TrendPage,
  },
  {
    path: '/mappage',
    component: MapPage,
  },
  {
    path: '/rankpage',
    component: RankPage,
  },
  {
    path: '/hotpage',
    component: HotPage,
  },
  {
    path: '/stockpage',
    component: StockPage,
  },
  {
    path: '/shanghai',
    component: shanghaiTrendPage,
  },
]

const router = new VueRouter({
  routes,
})

export default router
