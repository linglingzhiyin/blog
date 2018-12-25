module.exports = {
    baseUrl: '/',
    outputDir: 'dist',
    assetsDir: 'assets',
    devServer: {
        proxy: { // 配置跨域
            '/api': {
　　　　　　　　　　//要访问的跨域的api的域名
                target: 'http://127.0.0.1:9001/api',
                ws: true,
                changOrigin: true,
                pathRewrite: {
                    '^/api': ''
                }
            }
        },

    }
};