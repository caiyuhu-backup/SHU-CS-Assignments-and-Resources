module.exports = {
    productionSourceMap: false,
    //关闭eslint
    lintOnSave: false,
    //解决跨域问题
    devServer: {
        hot: true,
        disableHostCheck: true,
        proxy: {
            '/csky': { // 匹配所有以 csky 开头的请求路径
                target: 'http://localhost:8888/', // 代理目标的基础路径
                changeOrigin: true,  //用于控制请求头中的host值，即访问的来源地址，changeOrigin为true时会隐瞒其来源
                ws: true, // 用于支持websocket,
                secure: false, // 如果是https接口，需要配置这个参数
            }
        }
    }
}
