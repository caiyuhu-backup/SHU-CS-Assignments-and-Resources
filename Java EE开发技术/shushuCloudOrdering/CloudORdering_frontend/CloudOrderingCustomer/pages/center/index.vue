<template>
	<view class="center">
		<view class="center-card">
			<view class="u-flex u-row-left avatarStyle">
				<u-avatar :src="vuex_user === null ? '' : vuex_user.userImg"
					class="u-padding-10" size="150"></u-avatar>
				<view class="u-flex u-row-around" >
					<view class="u-m-l-12">欢迎, <u-icon name='level'></u-icon>
						<view style="font-size: 32rpx; color: #f29100; font-weight: bold;">
							{{vuex_user === null ? '' : vuex_user.nickname}}
						</view>
					</view>
					<view class="u-m-l-12" style="color: #909399;">手机号码：
						<u-icon name='phone-fill'></u-icon>
						{{vuex_user === null ? '' : vuex_user.userMobile}}
					</view>
				</view>
			</view>
			<view class="u-flex u-row-left u-padding-25">
				<u-button size="medium" type="warning" class="center-button" @click="toLoginPage">去注册</u-button>
				<u-button size="medium" type="error" class="center-button" @click="toLogout">退出登录</u-button>
			</view>
		</view>
		<u-card :show-head="false" :border="false" border-radius="35">
			<view class="" slot="body">
				<u-section title="最近常买" sub-title="查看更多" font-size=38 class="u-m-b-20" @click='toOrdersPage'></u-section>
				<u-empty mode="search" v-show="!oftenBuyProductList"></u-empty>
				<view class="u-flex u-row-left">
					<view class="u-col-center headBg u-m-r-20" v-for="(product, index) in oftenBuyProductList" :key="index" @click="toVendorPage(product.vendorId)">
						<image
							:src="product.productImg"
							mode="aspectFill"></image>
						<view class="u-body-item-title u-line-1" style="color: #f29100;">{{product.productName}}</view>
						<text>
							买过<text style="color: #E43D33; font-weight: bold; font-size: 40rpx;">{{product.buyCount}}</text>次
						</text>
					</view>
				</view>
			</view>
		</u-card>
		<u-action-sheet :list="logoutActionList" @click="logout" v-model="logoutActionShow"></u-action-sheet>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				logoutActionShow: false,
				logoutActionList: [{
					text: '确认退出',
					color: '#ff9900',
					fontSize: 35
				}],
				oftenBuyProductList: []
			}
		},
		onShow() {
			// console.log(this.$u.test.isEmpty(this.vuex_user));
			this.getUserInfo()
			this.getOftenBuyProductList()
		},
		onPullDownRefresh() {
			this.getUserInfo()
			setTimeout(function () {
				uni.stopPullDownRefresh()
			}, 600)
		},
		methods: {
			toOrdersPage() {
				this.$u.route({
					url: 'pages/orders/orders',
					type: 'tab'
				})
			},
			toVendorPage(vendorId) {
				this.$u.route({
					url: 'pages/vendor/vendor',
					params: {
						cid: vendorId
					}
				})
			},
			async getOftenBuyProductList() {
				if (this.$u.test.isEmpty(this.vuex_user)) {
					return
				}
				this.oftenBuyProductList = await this.$u.api.getOftenBuy(this.vuex_user.userId)
				console.log(this.oftenBuyProductList);
			},
			toLogout() {
				if (this.$u.test.isEmpty(this.vuex_user)) {
					this.$u.toast('请先登录~')
					return
				}
				this.logoutActionShow = true
			},
			async logout() {
				const res = await this.$u.api.logout()
				this.$u.vuex('vuex_token', null)
				this.$u.vuex('vuex_user', null)
				this.$u.toast('退出成功~')
			},
			async getUserInfo() {
				const userInfo = await this.$u.api.getUserInfo()
				console.log(userInfo)
				if (this.$u.test.isEmpty(userInfo)) {
					this.toLoginPage()
					return
				}
				this.$u.vuex('vuex_user', userInfo)
			},
			toLoginPage() {
				this.$u.route({
					url: 'pages/login/login',
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.center {
		margin-top: 18rpx;
	}
	
	.avatarStyle {
		
	}

	.center-button {
		border-radius: 50rpx;
		width: 90rpx;
	}
	
	.center-card {
		width: 92%;
		height: 330rpx;
		background-color: #ffffff;
		border-radius: 35rpx;
		margin: 30rpx;
		padding: 10rpx;
	}
</style>
<style scoped lang="scss">
	image {
		width: 150rpx;
		flex: 0 0 120rpx;
		height: 200rpx;
		border-radius: 8rpx;
	}

	.u-body-item-title {
		font-weight: bold;
	}

	.headBg {
		width: 150rpx;
		background: #fdf6ec;
	}
	
</style>
