<!-- 商家分布图表 -->
<template>
  <div class = 'com-container' @dblclick = "revertMap">
    <div class = 'com-chart' ref = 'map_ref'></div>
  </div>
</template>

<script>
import axios from 'axios'
import { getProvinceMapInfo } from '@/utils/map_utils'

export default {
  data () {
    return {
      chartInstance: null,
      allData: null,
      mapData: {}, // 所获取的省份的地图矢量数据
    }
  },
  mounted () {
    this.initChart()
    window.addEventListener('resize', this.screenAdapter)
    this.screenAdapter()
  },
  destroyed () {
    window.removeEventListener('resize', this.screenAdapter)
  },
  methods: {
    async initChart () {
      this.chartInstance = this.$echarts.init(this.$refs.map_ref, 'chalk')
      // 获取中国地图的矢量数据
      // http://localhost:8999/static/map/china.json
      // 由于我们现在获取的地图矢量数据并不是位于KOA2的后台, 所以咱们不能使用this.$http
      const ret = await axios.get('https://api.itbear666.top/models/china.json')
      this.$echarts.registerMap('china', ret.data)
      await this.getData()
      const initOption = {
        title: {
          text: '▎ 全国现有确诊',
          left: 20,
          top: 20,
        },
        tooltip: {
          trigger: 'item',
        },
        toolbox: {
          show: true,
          feature: {
            saveAsImage: {},
            dataView: {
              readOnly: false,
            },
          },
        },
        geo: [
          {
            zlevel: 2,//geo显示级别，默认是0
            map: 'china',//地图名
            roam: false,//设置为false,不启动roam就无所谓缩放拖曳同步了
            zoom: 1.2,//缩放级别
            itemStyle: {//顶层地图的样式，如地图区域颜色，边框颜色，边框大小等
              normal: {
                areaColor: '#08407D',
                borderColor: '#00FFFF',
                borderWidth: 0.3,
              },

            },
          }, {
            map: 'china',
            roam: false,//roam与上一个geo配置相同
            zoom: 1.2,
            itemStyle: {//底层地图样式
              normal: {
                //此处border设置你想要显示的地图外边框样式，可设置border颜色，阴影等
                borderColor: '#0A609D',
                borderWidth: 2,
              },
            },
          },
        ],

        legend: {
          show: false,
          left: '5%',
          bottom: '5%',
          orient: 'vertical',
        },
        series: [
          {
            name: '现有确诊人数',
            type: 'map',
            geoIndex: 0,
            mapType: 'china',
            roam: false, //拖动和缩放
            data: this.allData.data,
          },
        ],
        visualMap: {
          type: 'piecewise',
          show: true,
          // x: 'left',
          // y: 'bottom',
          textStyle: {
            fontSize: 18,
            color: 'rgba(248, 247, 247, 1)',
          },
          splitList: [
            {
              start: 1,
              end: 9,
            },
            {
              start: 10,
              end: 99,
            },
            {
              start: 100,
              end: 999,
            },
            {
              start: 1000,
              end: 9999,
            },
            {
              start: 10000,
            }],
          color: ['#8A3310', '#C64918', '#E55B25', '#F2AD92', '#F9DCD1'],
        },
      }
      this.chartInstance.setOption(initOption)
    },
    async getData () {
      // 获取服务器的数据, 对this.allData进行赋值之后, 调用updateChart方法更新图表
      const { data: ret } = await this.$http.get('map')
      // 打印下看看
      this.allData = ret
      console.log(this.allData.data)
    },
    screenAdapter () {
      const titleFontSize = this.$refs.map_ref.offsetWidth / 100 * 3.6
      const adapterOption = {
        title: {
          textStyle: {
            fontSize: titleFontSize,
          },
        },
        legend: {
          itemWidth: titleFontSize / 2,
          itemHeight: titleFontSize / 2,
          itemGap: titleFontSize / 2,
          textStyle: {
            fontSize: titleFontSize / 2,
          },
        },
      }
      this.chartInstance.setOption(adapterOption)
      this.chartInstance.resize()
    }
    ,
  },
}
</script>

<style lang = 'less' scoped>
</style>
