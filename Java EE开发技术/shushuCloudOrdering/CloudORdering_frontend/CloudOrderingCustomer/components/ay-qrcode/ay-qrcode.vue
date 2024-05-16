<template>
	<view :class="modal?'show-qrcode':'hide-qrcode'" :style="qrCodeStyle">
		<view class="box-qrcode" :style="{'margin-left':  marginLeft + 'px'}" @longtap="longtapCode">
			<!-- style="width: 550rpx;height: 550rpx;" -->
			
			<canvas class="canvas-qrcode" :style="style_w_h" :canvas-id="qrcode_id">
				
				<!-- #ifndef MP -->
				<view v-if="modal&&is_themeImg" :style="style_w_h" class="box-img-qrcode">
					<image :style="style_w_h_img" mode="scaleToFill" :src="themeImg"></image>
				</view>
				<!-- #endif -->
				
			</canvas>
			
			<!-- <image mode="scaleToFill" :src="imagePath"></image> -->
			
		</view>
	</view>
</template>

<script>
	var qr_we = require("./qrcode_wx.js");
	const qrCode = require('./weapp-qrcode.js')
	export default {
		data() {
			return {
				isAndroid : false ,
				show: true,
				imagePath: '',
				// qrcode_id: 'qrcode_id',
				marginLeft: 0,
				//一般的安卓app只需加30就能显示全
				//苹果app的不加就能显示全，加了就要弄margin-left
				//有些安卓app显示不全
				add_num : 30 ,
				add_num_key : 'rectify_code_key',
			}
		},
		props: {
			modal: {
				type: Boolean,
				default: false
			},
			isEffect: {
				type: Boolean,
				default: true
			},
			url: {
				type: String,
				default: ''
			},
			height: {
				type: Number,
				default: 260
			},
			width: {
				type: Number,
				default: 260
			},
			themeColor: {
				type: String,
				default: '#333333',
			},
			qrcode_id: {
				type: String,
				default: 'qrcode_id',
			},
			is_themeImg: {
				type: Boolean,
				default: false,
			},
			themeImg: {
				type: String,
				default: 'https://cdn.pixabay.com/photo/2016/11/29/13/24/balloons-1869816__340.jpg',
			},
			h_w_img: {
				type: Number,
				default: 30
			},
			
			
		},
		watch:{
			
		},
		computed: {
			style_w_h() {
				return this.set_style_w_h();
			},
			style_w_h_img() {
				let that = this;
				var height = parseInt(that.h_w_img);
				var width = parseInt(that.h_w_img);
				var style = '';
				if (height > 0) {
					style = `height:${height*2}rpx;`;
				}
				if (width > 0) {
					style += `width:${width*2}rpx;z-index: 2;`;
				}
			
				return style;
			},
			qrCodeStyle() {
				if (!this.isEffect)
					return {
						"opacity": "0.3",
						"filter": "alpha(opacity=60)"
					}
				return {}
			}
		},
		created: function() {
			let that = this;
			try {
				//app苹果二维码不居中
				//#ifndef MP
				let isAndroid = false ;
			    const res = uni.getSystemInfoSync();
			    if(res.platform == 'android'){
					isAndroid = true ;
				}else{
					isAndroid = false ;
				}
				
				
				if (!isAndroid) {
					that.marginLeft = 46;
				}
				
				that.isAndroid = isAndroid ;
				try {
					const add_num = uni.getStorageSync(that.add_num_key);
					if (add_num) {
						that.add_num = add_num;
					}
					
				} catch (e) {
					// error
				
				}
				// #endif

			} catch (e) {
			    // error
			}

			//#ifdef MP
			//that.marginLeft = 40;
			// #endif

		},
		methods: {
			set_style_w_h(){
				
				let that = this;
				var height = parseInt(that.height);
				var width = parseInt(that.width);
				var style = '';
				var height = height*2 ;
				var width = width*2 ;
				
				//#ifndef MP
				var add = that.add_num ;
				
				height +=  add;
				width +=  add;
				// #endif
				
				if (height > 0) {
					style = `height:${height}rpx;`;
				}
				if (width > 0) {
					style += `width:${width}rpx;`;
				}
				
				return style;
			},
			hideQrcode() {
				this.$emit("hideQrcode")
			},
			// 二维码生成工具
			crtQrCode() {
				let that = this;
				//#ifndef MP
				new qrCode(that.qrcode_id, {
					text: this.url,
					width: that.width,
					height: that.height,
					colorDark: that.themeColor,//#333333
					colorLight: "#FFFFFF",
					correctLevel: qrCode.CorrectLevel.H,
				})
				// #endif
				//#ifdef MP
				that.createQrCode(this.url, that.qrcode_id, that.width, that.height,that.themeColor,that.is_themeImg,that.themeImg,that.h_w_img);
				// #endif

				//that.createQrCode(this.url, that.qrcode_id, that.width, that.height);
			},
			//#ifdef MP

			createQrCode: function(url, canvasId, cavW, cavH,cavColor,haveImg,imgurl,imgsize) {
				//调用插件中的draw方法，绘制二维码图片
				qr_we.api.draw(url, canvasId, cavW, cavH,cavColor,haveImg,imgurl,imgsize, this, this.canvasToTempImage);
				// setTimeout(() => { this.canvasToTempImage();},100);

			},
			
			// #endif
			//获取临时缓存照片路径，存入data中
			canvasToTempImage: function() {
				var that = this;
			},
			saveImage: function() {
				var that = this;
				uni.canvasToTempFilePath({
					canvasId: that.qrcode_id,
					success: function(res) {
						var tempFilePath = res.tempFilePath;
						console.log(tempFilePath);
						that.imagePath = tempFilePath;
						
						//保存到相册
						// uni.saveFile({
						//       tempFilePath: tempFilePath,
						//       success: function (res2) {
						//         var savedFilePath = res2.savedFilePath;
								
								
						//       }
						// });
						uni.saveImageToPhotosAlbum({
							filePath : tempFilePath ,
							success: function (res3) {
								uni.showModal({
									title: '提示',
									content: '保存成功',
									confirmText: '确定',
									showCancel: false,
									confirmColor: '#33CCCC',
									success(res4) {
										
									}
								}) 
							},
						});
					},
					fail: function(res) {
						console.log(res);
					}
				}, that);
			},
			//微信小程序支持：长按二维码，提示是否保存相册
			//安卓APP长按校正二维码
			longtapCode(){
				var that = this;
				
				//#ifndef MP
				// uni.showModal({
				// 	title: '校正二维码',
				// 	content: '二维码是否异常',
				// 	confirmText: '确定',
				// 	confirmColor: '#33CCCC',
				// 	success(res) {
				// 		if (res.confirm) {
				// 			that.rectify_code();
				// 		}
				// 	}
				// })
				// #endif
				
				//#ifdef MP-WEIXIN
				uni.showModal({
					title: '提示',
					content: '是否保存到相册',
					confirmText: '确定',
					confirmColor: '#33CCCC',
					success(res) {
						if (res.confirm) {
							that.saveImage();
						}
					}
				})
				// #endif
			},
			//安卓有些手机不正常，长按可选择矫正
			rectify_code(){
				var that = this;
				let add_num = that.add_num ;
				add_num += 30 ;
				that.add_num = add_num;
				that.crtQrCode();//重新生成才会立即覆盖
				try {
					//第一次长按校正设置了就不用在设置
					uni.setStorage({
						key: that.add_num_key,
						data: add_num,
						success: function() {
							
						}
					});
				} catch (e) {
					// error
				
				}
			},
		},
		mounted() {}
	}
</script>

<style scoped lang="scss">
	// .qrcode-box {
	// 	position: fixed;
	// 	left: 0;
	// 	top: 0;
	// 	right: 0;
	// 	bottom: 0;
	// 	height: 100vh;
	// 	width: 100vw;
	// 	background-color: rgba(59, 59, 59, 0.6);
	// 	// opacity: 0.8;
	// 	text-align: center;
	// 	display: flex;
	// 	align-items: center;
	// 	display: none;

	// 	.qrcode-item {
	// 		flex: 1;
	// 		position: relative;
	// 		text-align: center;

	// 		.item-box {
	// 			width: 90%;
	// 			margin: auto;
	// 			display: inline-block;
	// 			margin-top: 30%;
	// 			padding-bottom: 30rpx;

	// 			// animation: show 0.7s;
	// 			.title {
	// 				font-size: 46rpx;
	// 				text-align: center;
	// 				margin-bottom: 24rpx;
	// 			}

	// 			.canvas {
	// 				margin: auto;
	// 				display: inline-block;
	// 				margin: auto;
	// 			}

	// 			background-color: #FFFFFF;
	// 		}

	// 	}
	// }
	.box-qrcode{
		text-align: center;
		position: relative;
		.box-img-qrcode{
			position: absolute;
			display: flex;
			flex-direction: column;
			justify-content: center;
			align-items: center;
			z-index: 2;
		}
	}
	image{
		width: 60upx;
		height: 60upx;
		border-radius: 50%;
		
	}
	.canvas-qrcode {
		
		margin: auto;
		display: inline-block;
		float: left;
	}
	
	
	.opacity-qrcode {
		opacity: 0;
		display: block;
	}

	.show-qrcode {
		display: block;
		animation: fade 0.7s;

		// -moz-animation: fade 0.5s; /* Firefox */
		// -webkit-animation: fade 0.5s; /* Safari 和 Chrome */
		// -o-animation: fade 0.5s;
	}

	.hide-qrcode {
		animation: hide 0.7s;
	}

	@keyframes fade {
		from {
			opacity: 0.8;
		}

		to {
			opacity: 1;
		}
	}

	@keyframes hide {
		from {
			opacity: 1;
		}

		to {
			opacity: 0;
		}
	}
</style>
