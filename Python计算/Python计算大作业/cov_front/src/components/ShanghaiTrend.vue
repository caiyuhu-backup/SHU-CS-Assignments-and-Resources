<template>
  <div class = "com-container">
    <div class = "title" :style = "comStyle">
      <span>{{ '▎ ' + '上海新增趋势' }}</span>
      <div class = "select-con" :style = "marginStyle"></div>
    </div>
    <div class = "com-chart" ref = "trend_ref"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      chartInstane: null,
      allData: null, // 从服务器中获取的所有数据
      titleFontSize: 12, // 指明标题的字体大小
    }
  },
  mounted () {
    this.initChart()
    this.getData()
    window.addEventListener('resize', this.screenAdapter)
    this.screenAdapter()
  },
  destroyed () {
    window.removeEventListener('resize', this.screenAdapter)
  },
  computed: {
    selectTypes () {
      if (!this.allData) {
        return []
      } else {
        return this.allData.type.filter(item => {
          return item.key !== this.choiceType
        })
      }
    },
    // 设置给标题的样式
    comStyle () {
      return {
        fontSize: this.titleFontSize + 'px',
      }
    },
    marginStyle () {
      return {
        marginLeft: this.titleFontSize + 'px',
      }
    },
  },
  methods: {
    initChart () {
      this.chartInstane = this.$echarts.init(this.$refs.trend_ref, 'chalk')
      const initOption = {
        toolbox: {
          show: true,
          feature: {
            magicType: {
              type: ['line', 'bar', 'stack'],
            },
            saveAsImage:{},
            dataView: {
              readOnly: false
            },
          },
        },
        grid: {
          left: '3%',
          top: '35%',
          right: '4%',
          bottom: '1%',
          containLabel: true,
        },
        tooltip: {
          trigger: 'axis',
        },
        legend: {
          left: 20,
          top: '15%',
          icon: 'circle',
        },
        xAxis: {
          type: 'category',
          boundaryGap: false,
        },
        yAxis: {
          type: 'value',
        },
      }
      this.chartInstane.setOption(initOption)
    },
    async getData () {
      // await this.$http.get()
      // 对allData进行赋值
      const { data: ret } = await this.$http.get('shanghai')
      this.allData = ret
      console.log(this.allData)
      console.log(this.allData.day)
      this.updateChart()
    },
    updateChart () {
      // 半透明的颜色值
      const colorArr1 = [
        'rgba(11, 168, 44, 0.5)',
        'rgba(44, 110, 255, 0.5)',
        'rgba(22, 242, 217, 0.5)',
        'rgba(229,179,51,0.5)',
      ]
      // 全透明的颜色值
      const colorArr2 = [
        'rgba(11, 168, 44, 0)',
        'rgba(44, 110, 255, 0)',
        'rgba(22, 242, 217, 0)',
        'rgba(229,179,51,0)',
      ]
      // 处理数据
      // 类目轴的数据
      const timeArr = this.allData.day
      // y轴的数据 series下的数据
      const seriesArr = [
        {
          name: '现有疑似新增',
          type: 'line',
          data: this.allData.suspect_add_ls,
          stack: 'total',
          markPoint: {
            data: [
              {
                type: 'max',
                name: 'Max',
              }, {
                type: 'min',
                name: 'Min',
              }],
          },
          areaStyle: {
            color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: colorArr1[0],
              }, // %0的颜色值
              {
                offset: 1,
                color: colorArr2[0],
              }, // 100%的颜色值
            ]),
          },
        },
        {
          name: '现有死亡新增',
          type: 'line',
          data: this.allData.dead_add_ls,
          stack: 'total',
          markPoint: {
            data: [
              {
                type: 'max',
                name: 'Max',
              }, {
                type: 'min',
                name: 'Min',
              }],
          },
          areaStyle: {
            color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: colorArr1[1],
              }, // %0的颜色值
              {
                offset: 1,
                color: colorArr2[1],
              }, // 100%的颜色值
            ]),
          },
        },
        {
          name: '现有治愈新增',
          type: 'line',
          data: this.allData.heal_add_ls,
          stack: 'total',
          markPoint: {
            data: [
              {
                type: 'max',
                name: 'Max',
              }, {
                type: 'min',
                name: 'Min',
              }],
          },
          areaStyle: {
            color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: colorArr1[2],
              }, // %0的颜色值
              {
                offset: 1,
                color: colorArr2[2],
              }, // 100%的颜色值
            ]),
          },
        },
        {
          name: '现有确诊新增',
          type: 'line',
          data: this.allData.confirm_add_ls,
          stack: 'total',
          markPoint: {
            data: [
              {
                type: 'max',
                name: 'Max',
              }, {
                type: 'min',
                name: 'Min',
              }],
          },
          areaStyle: {
            color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [
              {
                offset: 0,
                color: colorArr1[3],
              }, // %0的颜色值
              {
                offset: 1,
                color: colorArr2[3],
              }, // 100%的颜色值
            ]),
          },
        },

      ]
      const dataOption = {
        xAxis: {
          data: timeArr,
        },
        legend: {
          data: ['现有疑似新增', '现有死亡新增', '现有治愈新增', '现有确诊新增'],
        },
        series: seriesArr,
      }
      this.chartInstane.setOption(dataOption)
    },
    screenAdapter () {
      this.titleFontSize = this.$refs.trend_ref.offsetWidth / 100 * 3.6
      const adapterOption = {
        legend: {
          itemWidth: this.titleFontSize / 2,
          itemHeight: this.titleFontSize / 2,
          itemGap: this.titleFontSize / 2,
          textStyle: {
            fontSize: this.titleFontSize / 2,
          },
        },
      }
      this.chartInstane.setOption(adapterOption)
      this.chartInstane.resize()
    },
    handleSelect (currentType) {
      this.choiceType = currentType
      this.updateChart()
      this.showChoice = false
    },
  },
}
</script>

<style lang = "less" scoped>
.title {
  position: absolute;
  left: 20px;
  top: 20px;
  z-index: 10;
  color: white;

  .title-icon {
    margin-left: 10px;
    cursor: pointer;
  }

  .select-con {
    background-color: #222733;
  }
}
</style>
