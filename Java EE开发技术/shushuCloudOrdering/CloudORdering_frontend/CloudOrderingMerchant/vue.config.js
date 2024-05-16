module.exports = {
	configureWebpack: {
		devServer: {
			disableHostCheck: true
		}
	},

	// devServer: {
	// 	port: '80',
	// 	disableHostCheck: true,
	// 	proxy: {
	// 		'/api': {
	// 			target: 'http://localhost:80',
	// 			changeOrigin: false
	// 		}
	// 	}
	// }
}
