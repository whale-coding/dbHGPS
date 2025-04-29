const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  publicPath: '/',  // 配置项目发布路径
  transpileDependencies: true,
  configureWebpack: {
    externals: {
      'echarts': 'echarts',
    }
  }
})