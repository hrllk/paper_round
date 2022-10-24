module.exports = {
  "transpileDependencies": [
    "vuetify"
  ],
  // publicPath: '/material-vue/'
  devServer: {
    proxy: 'http://localhost:8100'
  }
}
