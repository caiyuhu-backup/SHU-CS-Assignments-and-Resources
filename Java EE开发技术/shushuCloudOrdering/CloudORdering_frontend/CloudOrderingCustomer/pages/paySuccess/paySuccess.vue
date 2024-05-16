<template>
	<view class="pay-card">
		<u-navbar back-text="返回" title="取餐二维码" :custom-back='toHome'></u-navbar>
		<view class="u-flex u-row-center u-m-t-20" style="color: #303133; font-size: 1.6em; font-weight: bold;">
			<u-icon name='account-fill'></u-icon>
			{{payInfo.userName}}
		</view>
		<view class="u-flex u-row-center u-m-t-20" style="color: #909399; font-size: 1.6em;">
			<u-icon name='phone'></u-icon>
			{{payInfo.userMobile}}
		</view>
		<view class="u-flex u-row-center u-m-t-20 u-font-lg">
			请于⏳
		</view>
		<view class="u-flex u-row-center u-m-t-15">
			<view class="pay-time">{{payInfo.mealTime}}之后</view>
		</view>
		<view class="u-flex u-row-center u-m-t-15 u-font-lg">
			凭👇二维码领取🍗
		</view>
		<view class="u-flex u-row-center u-m-t-30">
			<ayQrcode ref="qrcode" :modal="modal_qr" :url="payInfo.receiverQRCode" @hideQrcode="hideQrcode" :isEffect="isEffect" :height="220" :width="220"/>
		</view>
		<view class="u-flex u-row-center" v-show="isEffect">
			<view class="pay-status">状态：{{status}}</view>
		</view>
		<view class="u-flex u-row-center" v-show="!isEffect">
			<view class="pay-status2">状态：{{status}}</view>
		</view>
		<u-line color="red" border-style="dashed" margin="20rpx"/>
		<view class="u-flex u-row-center u-m-t-10" v-for="(item, index) in payInfo.productOrderVos" :key="item.productId">
			<text class="u-m-r-20" style="font-size: 1.2em; color: #f29100; font-weight: bold;">{{item.productName}}</text>* 
			<text class="u-m-l-20" style="font-size: 1.2em; color: #f29100; font-weight: bold;">{{item.buyCounts}}</text>
		</view>
		<view class="u-flex u-row-center u-m-t-30" style="color: #909399; font-size: 1.2em;">
			订单备注：{{payInfo.orderRemark === '' ? '无' : payInfo.orderRemark}}
		</view>
		<view class="u-flex u-row-center u-m-t-30">
			订单生成时间：{{payInfo.orderTime}}
		</view>
	</view>
</template>

<script>
	import ayQrcode from "@/components/ay-qrcode/ay-qrcode.vue"
	export default {
		components: {
			ayQrcode,
		},
		data() {
			return {
				modal_qr: false,
				url: '',
				// 请求的订单信息
				payInfo: {},
				// 订单id
				orderId: '',
				// 订单状态
				status: '',
				isEffect: true,
				timer: 0
			}
		},
		onLoad(options) {
			let that = this	
			this.showQrcode() //一加载生成二维码
			this.orderId = options.orderId
			// 下拉刷新
			uni.startPullDownRefresh()
			
			this.queryByOrderId()
			this.getStatusByOrderId()
			
		},
		onPullDownRefresh() {
			// 请求二维码状态
			this.getStatusByOrderId()
			if (!this.isEffect) {
				uni.showToast({
					icon: "error",
					title: '二维码已失效~'
				})
			} else {
				uni.showToast({
					icon: "success",
					title: '刷新成功~'
				})
			}
			setTimeout(function () {
				uni.stopPullDownRefresh();
				uni.hideToast()
			}, 300);
		},
		onBackPress() {  
		  this.toHome()
		},
		methods: {
			toHome() {
				this.$u.route({
					url: 'pages/index/index',
					type: 'tab'
				})
			},
			// 获取订单信息
			async queryByOrderId() {
				const result = await this.$u.api.queryByOrderId(this.orderId)
				console.log(result)
				this.payInfo = result
			},
			// 获取订单状态
			async getStatusByOrderId() {
				const result = await this.$u.api.getStatusByOrderId(this.orderId)
				console.log(result)
				if (result === '1') {
					this.status = '待取餐'
				} else {
					this.status = '已完成'
					this.isEffect = false
				}
			},
			// 展示二维码
			showQrcode() {
				let _this = this
				this.modal_qr = true
				uni.showLoading()
				setTimeout(function() {
					uni.hideLoading()
					_this.$refs.qrcode.crtQrCode()
				}, 600)
			},
			//传入组件的方法
			hideQrcode() {
				this.modal_qr = false
			},
		}
	}
</script>

<style>
	page {
		background-color: #2ab952;
	}

	.pay-card {
		width: 92%;
		height: 100%;
		background-color: #ffffff;
		border-radius: 35rpx;
		margin: 30rpx;
		margin-top: 120rpx;
		padding: 10rpx;
	}
	
	.pay-time {
		color: #2ab952;
		font-weight: bold;
		font-size: 45rpx;
	}
	
	.pay-address {
		color: #f29100;
		font-weight: bold;
		font-size: 45rpx;
	}
	
	.pay-status {
		color: #dd6161;
		font-weight: bold;
		font-size: 50rpx;
	}
	
	.pay-status2 {
		color: #909399;
		font-weight: bold;
		font-size: 50rpx;
	}
</style>
