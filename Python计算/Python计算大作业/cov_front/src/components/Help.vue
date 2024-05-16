<!-- 商家销量统计的横向柱状图 -->
<template>
  <div class = "com-container">
    <div class = "com-chart" ref = "seller_ref"></div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      chartInstance: null,
      allData: null, // 服务器返回的数据
      currentPage: 1, // 当前显示的页数
      totalPage: 0, // 一共有多少页
      timerId: null, // 定时器的标识
    }
  },
  mounted () {
    this.initChart()
    this.getData()
    window.addEventListener('resize', this.screenAdapter)
    // 在页面加载完成的时候, 主动进行屏幕的适配
    this.screenAdapter()
  },
  destroyed () {
    clearInterval(this.timerId)
    // 在组件销毁的时候, 需要将监听器取消掉
    window.removeEventListener('resize', this.screenAdapter)
  },
  methods: {
    // 初始化echartInstance对象
    initChart () {
      this.chartInstance = this.$echarts.init(this.$refs.seller_ref, 'chalk')
      // 对图表初始化配置的控制
      const initOption = {
        title: {
          text: '▎上海求助信息统计',
          left: 20,
          top: 20,
          textStyle: {
            fontSize: 50,
          },
        },
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
          top: '20%',
          left: '3%',
          right: '6%',
          bottom: '3%',
          containLabel: true, // 距离是包含坐标轴上的文字
        },
        xAxis: {
          type: 'value',
        },
        yAxis: {
          type: 'category',
          data: [
            '浦东新区',
            '宝山区',
            '闵行区',
            '浦东区',
            '普陀区',
            '徐汇区',
            '静安区',
            '杨浦区',
            '嘉定区',
            '长宁区',
            '黄浦区',
            '虹口区',
            '松江区',
            '青浦区',
            '奉贤区',
            '崇明区',
            '金山区',
            '闸北区'],
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'line',
            z: 0,
            lineStyle: {
              color: '#2D3443',
            },
          },
        },
        series: [
          {
            type: 'bar',
            markPoint: {
              data: [{
                type: "max",
                name: "Max"
              }, {
                type: "min",
                name: "Min"
              }]
            },
            markLine: {
              data: [{
                type: "average",
                name: "Avg"
              }]
            },
            label: {
              show: true,
              position: 'right',
              textStyle: {
                color: 'white',
              },
            },
            itemStyle: {
              // 指明颜色渐变的方向
              // 指明不同百分比之下颜色的值
              color: new this.$echarts.graphic.LinearGradient(0, 0, 1, 0, [
                // 百分之0状态之下的颜色值
                {
                  offset: 0,
                  color: '#5052EE',
                },
                // 百分之100状态之下的颜色值
                {
                  offset: 1,
                  color: '#AB6EE5',
                },
              ]),
            },
            data: [
              1601,
              1205,
              1142,
              1088,
              869,
              860,
              723,
              667,
              603,
              504,
              487,
              445,
              433,
              281,
              137,
              71,
              60,
              27],
          },
        ],
      }
      this.chartInstance.setOption(initOption)
      // 对图表对象进行鼠标事件的监听
    },
  },
}
</script>

<style lang = "less" scoped>


</style>
