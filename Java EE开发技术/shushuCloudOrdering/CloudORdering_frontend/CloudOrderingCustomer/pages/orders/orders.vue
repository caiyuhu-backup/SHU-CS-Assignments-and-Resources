<template>
	<view class="wrap">
		<view class="u-tabs-box">
			<u-tabs-swiper activeColor="#f29100" ref="tabs" :list="list" :current="current" @change="change" 
			:is-scroll="false" swiperWidth="750" style="position: relative;"></u-tabs-swiper>
		</view>
		<swiper class="swiper-box" :current="swiperCurrent" @transition="transition" @animationfinish="animationfinish">
			<swiper-item class="swiper-item" v-for="(index) in 3" :key="index">
				<scroll-view scroll-y style="height: 98%;width: 100%;" @scrolltolower="reachBottom" v-show="orderList.length > 0">
					<view class="page-box">
						<view class="order" v-for="(res, index) in orderList" :key="index">
							<view class="top">
								<view class="left" @click="toVendor(res.vendorId)">
									<u-icon name="home" :size="30" color="rgb(94,94,94)"></u-icon>
									<view class="store">{{res.vendorName}}</view>
									<u-icon name="arrow-right" color="rgb(203,203,203)" :size="26"></u-icon>
								</view>
								<view class="right" v-show="status === 1">待取餐</view>
								<view class="right" v-show="status === 2">待评价</view>
								<view class="right" v-show="status === 3">已完成</view>
							</view>
							<u-read-more :toggle="true" show-height="500" close-text='查看更多' color='#dd6161'>
								<view class="item" v-for="(item, index) in res.productOrderVoList" :key="index" @click="toPaySuccess(res.orderId)">
									<view class="left"><image :src="item.productImg" mode="aspectFill"></image></view>
									<view class="content">
										<view class="title u-line-3">{{ item.productName }}</view>
										<view class="type">{{ item.type }}</view>
									</view>
									<view class="right">
										<view class="price">
											￥<text class="decimal">{{item.sellPrice}}</text>
										</view>
										<view class="number">x{{ item.buyCounts }}</view>
									</view>
								</view>
							</u-read-more>
							<view class="total">
								共{{res.productTotalCount}}件商品 合计:
								<text class="total-price">
									￥<text class="decimal">{{res.totalAmount}}</text>
								</text>
							</view>
							<view class="bottom">
								<view class="more"><u-icon name="more-dot-fill" color="rgb(203,203,203)"></u-icon></view>
								<view class="logistics btn" @click="toPaySuccess(res.orderId)">查看订单</view>
								<view class="evaluate btn" @click="toPaySuccess(res.orderId)" v-show="status === 1">去取餐</view>
								<view class="evaluate btn" @click="toComment(res.orderId)" v-show="status === 2">去评价</view>
								<view class="evaluate btn" v-show="status === 3">已完成</view>
							</view>
						</view>
						<u-loadmore :status="loadStatus" bgColor="#f2f2f2"></u-loadmore>
					</view>
				</scroll-view>
				<scroll-view scroll-y style="height: 100%;width: 100%;" v-show="orderList.length === 0">
					<view class="page-box">
						<view>
							<view class="centre">
								<image src="https://cdn.uviewui.com/uview/template/taobao-order.png"></image>
								<view class="explain">
									您还没有相关的订单
									<view class="tips">可以去看看有那些想吃的</view>
								</view>
								<view class="btn" @click="toHome">随便逛逛</view>
							</view>
						</view>
					</view>
				</scroll-view>
			</swiper-item>
		</swiper>
		<u-back-top :scroll-top="scrollTop"></u-back-top>
	</view>
</template>

<script>
export default {
	data() {
		return {
			list: [
				{
					name: '待取餐'
				},
				{
					name: '待评价',
					count: 0
				},
				{
					name: '已完成'
				}
			],
			current: 0,
			swiperCurrent: 0,
			tabsHeight: 0,
			dx: 0,
			loadStatus: 'loadmore',
			currentPage: 1,
			orderList: [],
			status: 1,
			scrollTop: 0,
		};
	},
	onPageScroll(e) {
		this.scrollTop = e.scrollTop;
	},
	async onShow() {
		await this.getOrderInfoList()
	},
	onHide() {
		this.orderList = []
	},
	onPullDownRefresh() {
		this.currentPage = 1
		this.orderList = []
		this.getOrderInfoList()
		setTimeout(function () {
			uni.stopPullDownRefresh()
		}, 800)
	},
	watch: {
		current(cur) {
			uni.showLoading({
				title: '加载中...'
			})
			this.status = cur + 1
			this.currentPage = 1
			this.orderList = []
			this.getOrderInfoList()
			// 给待评价添加count
			if (cur === 1) {
				this.list.count = this.orderList.length
			}
			setTimeout(()=>{
				uni.hideLoading()
			}, 1200)
		}
	},
	methods: {
		toHome() {
			this.$u.route({
				url: 'pages/index/index',
				type: 'tab'
			})
		},
		toComment(orderId) {
			this.$u.route({
				url: 'pages/userComment/userComment',
				params: {
					orderId: orderId
				}
			})
		},
		toPaySuccess(orderId) {
			this.$u.route({
				url: 'pages/paySuccess/paySuccess',
				params: {
					orderId: orderId
				}
			})
		},
		toVendor(vendorId) {
			console.log(vendorId);
			this.$u.route({
				url: 'pages/vendor/vendor',
				params: {
					cid: vendorId
				}
			})
		},
		async getOrderInfoList() {
			if (this.$u.test.isEmpty(this.vuex_user)) {
				this.$u.route({
					url: 'pages/route/login'
				})
				return
			}
			const result = await this.$u.api.queryOrderByUId({
				userId: this.vuex_user.userId,
				status: this.status,
				page: {
					current: this.currentPage,
					size: 3
				}
			})
			this.orderList = this.orderList.concat(result.orderBriefVoList)
			
			console.log(this.orderList)
		},
		reachBottom() {
			if(this.orderList.length > 0) {
				this.loadStatus= "loading"
				this.currentPage ++
				let preLength = this.orderList.length
				this.getOrderInfoList()
				let curLength = this.orderList.length
				if (preLength === curLength) {
					this.loadStatus = 'nomore'
				}
			}
		},
		// tab栏切换
		change(index) {
			this.swiperCurrent = index
		},
		transition({ detail: { dx } }) {
			this.$refs.tabs.setDx(dx)
		},
		animationfinish({ detail: { current } }) {
			this.$refs.tabs.setFinishCurrent(current)
			this.swiperCurrent = current
			this.current = current
		}
	}
};
</script>

<style>
/* #ifndef H5 */
page {
	height: 100%;
	background-color: #f2f2f2;
}
/* #endif */
</style>

<style lang="scss" scoped>
.order {
	width: 710rpx;
	background-color: #ffffff;
	margin: 20rpx auto;
	border-radius: 20rpx;
	box-sizing: border-box;
	padding: 20rpx;
	font-size: 28rpx;
	.top {
		display: flex;
		justify-content: space-between;
		.left {
			display: flex;
			align-items: center;
			.store {
				margin: 0 10rpx;
				font-size: 32rpx;
				font-weight: bold;
			}
		}
		.right {
			color: $u-type-warning-dark;
		}
	}
	.item {
		display: flex;
		justify-content: space-between;
		margin: 20rpx 0 0;
		.left {
			margin-right: 20rpx;
			image {
				width: 200rpx;
				height: 200rpx;
				border-radius: 10rpx;
			}
		}
		.content {
			.title {
				font-size: 28rpx;
				line-height: 50rpx;
				color: #303133;
				font-weight: bold;
			}
		}
		.right {
			margin-left: 10rpx;
			color: #fa3534;
			font-size: 30rpx;
			.decimal {
				margin-top: 4rpx;
			}
			.number {
				color: $u-tips-color;
				font-size: 28rpx;
			}
		}
	}
	.total {
		margin-top: 20rpx;
		text-align: right;
		font-size: 24rpx;
		.total-price {
			font-size: 35rpx;
		}
	}
	.bottom {
		display: flex;
		margin-top: 40rpx;
		padding: 0 10rpx;
		justify-content: space-between;
		align-items: center;
		.btn {
			line-height: 52rpx;
			width: 160rpx;
			border-radius: 26rpx;
			border: 2rpx solid $u-border-color;
			font-size: 26rpx;
			text-align: center;
			color: $u-type-info-dark;
		}
		.evaluate {
			color: $u-type-warning-dark;
			border-color: $u-type-warning-dark;
		}
	}
}
.centre {
	text-align: center;
	margin: 200rpx auto;
	font-size: 32rpx;
	image {
		width: 164rpx;
		height: 164rpx;
		border-radius: 50%;
		margin-bottom: 20rpx;
	}
	.tips {
		font-size: 24rpx;
		color: #999999;
		margin-top: 20rpx;
	}
	.btn {
		margin: 80rpx auto;
		width: 200rpx;
		border-radius: 32rpx;
		line-height: 64rpx;
		color: #ffffff;
		font-size: 26rpx;
		background: linear-gradient(270deg, rgba(249, 116, 90, 1) 0%, rgba(255, 158, 1, 1) 100%);
	}
}
.wrap {
	display: flex;
	flex-direction: column;
	height: calc(100vh - var(--window-top));
	width: 100%;
}
.swiper-box {
	flex: 1;
}
.swiper-item {
	height: 100%;
}
</style>
