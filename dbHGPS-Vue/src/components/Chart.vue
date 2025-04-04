<template>
    <div id="chart" style="width: 600px; height: 400px;"></div>
  </template>
  
  <script>
  import echarts from 'echarts'
  
  export default {
    name: 'Chart',
    mounted() {
      this.initChart()
    },
    methods: {
      initChart() {
        const chart = echarts.init(document.getElementById('chart'))
  
        const option = {
          title: {
            text: 'ECharts 示例'
          },
          tooltip: {
            trigger: 'axis'
          },
          legend: {
            data: ['销量']
          },
          xAxis: {
            type: 'category',
            data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子']
          },
          yAxis: {
            type: 'value'
          },
          series: [
            {
              name: '销量',
              type: 'bar',
              data: [5, 20, 36, 10, 10, 20]
            }
          ]
        }
  
        chart.setOption(option)
      }
    }
  }
  </script>