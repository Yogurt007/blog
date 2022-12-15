module.exports = {

  // 配置跨域请求
  devServer: {
    port: 8080,
    host: 'localhost',
    open: true,
    https: false,
    proxy: {
      '/sys': {
        target: 'http://localhost:8080/renren-fast/',
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/sys': ''
        }
      }
    }

  }

}
