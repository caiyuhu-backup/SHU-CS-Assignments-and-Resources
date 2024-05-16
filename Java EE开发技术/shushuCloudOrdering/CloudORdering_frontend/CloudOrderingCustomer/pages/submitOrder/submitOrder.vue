<template>
	<view>
		<u-picker v-model="showPickerTime" mode="time" :params="pickerTimeParams" title="领餐时间"
			@confirm="choosePickerTime" :default-time="defaulTime"></u-picker>
		<view class="mycard1">
			<u-section title="领餐时间" class="u-m-r-30 u-m-l-30 u-m-t-30" :show-line="false" font-size="32"
				@click="showPickerTime = true" :sub-title="pickerTimeStr" style="width: 92%;"></u-section>
			<u-section title="领餐地点" class="u-m-r-30 u-m-l-30 u-m-t-30" :show-line="false" font-size="32"
				style="width: 92%;" :arrow="false" :sub-title="vendorName"></u-section>
			<u-field v-model="vuex_user.realname" label="姓名" required maxlength="25" placeholder="请填写姓名"></u-field>
			<u-field maxlength="11" v-model="vuex_user.userMobile" :error-message="checkMobileText" label="手机号" required
				@blur="checkMobile" placeholder="请填写手机号">
			</u-field>
		</view>
		<u-card :title="vendorName" :sub-title="defaulTime" border-radius="35" :border="false" title-size="39" title-color="#f29100" :head-style="cardHeadStyle">
			<view class="" slot="body">
				<view class="u-body-item u-flex u-border-bottom u-col-between u-p-t-0" v-for="(goods, index) in itemList" :key="index">
					<image
						:src="goods.productImg"
						mode="aspectFill"></image>
					<view class="u-body-item-title u-line-3 u-m-l-12">{{goods.productName}}</view>
					<view class="u-body-item-price">￥{{goods.sellPrice}}</view>
					<view class="u-body-item-num">x{{goods.sale}}</view>
				</view>
			</view>
			<view slot="foot" class="u-flex u-row-right">
				<u-icon name="rmb-circle-fill" size="36"></u-icon>
				<view style="color: #dd6161;">
					<u-count-to :start-val="0" :end-val="totalPrice" :decimals="2" separator="," :duration="1000"
						:bold="true" font-size="40"></u-count-to>
				</view>
				元
			</view>
		</u-card>
		<view class="mycard2">
			<u-section title="备注" :sub-title="remarkSubTitle" class="mysection2" :show-line="false" font-size="32"
				@click="showRemark = true"></u-section>
			<u-popup v-model="showRemark" mode="top" border-radius="24" height="300" :closeable="true"
				close-icon-pos="bottom-right">
				<u-input v-model="userRemark" type="textarea" :border="true" height="185" :clearable="true"
					maxlength="75" style="margin: 25rpx;" placeholder="请输入备注..." />
			</u-popup>
		</view>
		<view class="navigation">
			<view class="left">
				<view class="item car">
					<u-badge class="car-num" :count="totalCount" type="error" :offset="[-3, -6]"></u-badge>
					<image src="../../static/pot.png" style="width: 65rpx; height: 65rpx;"></image>
				</view>
				<view style="color: #dd6161;">
					<text style="font-size: 40rpx;">￥</text>
					<u-count-to :start-val="0" :end-val="totalPrice" :decimals="2" separator="," :duration="1000" :bold="true"
						font-size="40"></u-count-to>
				</view>
			</view>
			<view class="right">
				<view class="buy btn u-line-1" @click="toPay">确认支付</view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				cardHeadStyle: {
					"fontWeight": "bold"
				},
				vendorName: '',
				showPickerTime: false,
				pickerTimeParams: {
					minute: true,
					month: true,
					day: true,
					hour: true,
				},
				pickerTime: '',
				mobile: '',
				username: '',
				checkMobileText: '',
				showRemark: false,
				userRemark: '',
				// 用户购买的商品列表
				itemList: [],
				totalCount: 0,
				totalPrice: 0.0,
				defaulTime: '',
				vendorId: 0
			}
		},
		onReady() {
			// 获取当前时间
			let currentTime = new Date().getTime()
			this.defaulTime = this.$u.timeFormat(currentTime, 'yyyy-mm-dd hh:MM:ss')
			this.pickerTime = this.$u.timeFormat(currentTime, 'yyyy-mm-dd hh:MM')
		},
		onLoad(options) {
			// console.log('123', options.itemList)
			// 获取传参
			this.itemList = JSON.parse(decodeURIComponent(options.itemList))
			// console.log(this.itemList);
			this.totalCount = options.totalCount
			this.totalPrice = options.totalPrice
			this.vendorName = options.vendorName
			this.vendorId = options.vendorId
		},
		computed: {
			// 备注说明
			remarkSubTitle() {
				if (this.$u.test.isEmpty(this.userRemark)) {
					return '点击填写'
				}
				return '已填写备注'
			},
			// 选择时间
			pickerTimeStr() {
				return this.pickerTime + "领取"
			}
		},
		methods: {
			// 检查手机号是否合规
			checkMobile() {
				if (!this.$u.test.mobile(this.mobile)) {
					this.checkMobileText = '手机号有误'
				} else {
					this.checkMobileText = ''
				}
			},
			// 计算时间格式
			choosePickerTime(time) {
				this.pickerTime = new Date().getFullYear() + '-' + time.month + "-" + time.day + " " + time.hour + ":" + time.minute
			},
			// 提交订单
			async toPay() {
				// 获取商品id和数量
				let productIdList = []
				let buyCountsList = []
				this.itemList.forEach(item=>{
					productIdList.push(item.productId)
					buyCountsList.push(item.sale)
				})
				this.pickerTime += ":00"
				console.log('提交订单', {
					userId: this.vuex_user.userId,
					vendorId: this.vendorId,
					receiverQrCode: this.$u.guid(64),
					productIdList,
					buyCountsList,
					mealTime: this.pickerTime,
					orderRemark: this.userRemark
				})
				// 提交订单
				const result = await this.$u.api.addOrder({
					userId: this.vuex_user.userId,
					vendorId: this.vendorId,
					receiverQrCode: this.$u.guid(64),
					productIdList,
					buyCountsList,
					mealTime: this.pickerTime,
					orderRemark: this.userRemark
				})
				uni.showToast({
					title: '支付成功',
					icon: "success"
				})
				// 将vendorItemList删除对于vendorId的部分
				this.$u.vuex('vendorItemList', this.vendorItemList.filter((item)=>item.vendorId !== this.vendorId))
				// 成功后进行路由跳转
				this.$u.route({
					url: 'pages/paySuccess/paySuccess',
					params: {
						orderId: result
					}
				})
			}
		}
	}
</script>
<style lang="scss" scoped>
	.navigation {
		display: flex;
		width: 92%;
		justify-content: space-between;
		margin: 25rpx 20rpx 30rpx 30rpx;
		border: solid 2rpx #f2f2f2;
		border-radius: 50rpx;
		background-color: #fdf6ec;
		padding: 16rpx 0;

		.left {
			display: flex;
			font-size: 20rpx;

			.item {
				margin: 0 30rpx;

				&.car {
					text-align: center;
					position: relative;

					.car-num {
						position: absolute;
						top: -10rpx;
						right: -10rpx;
					}
				}
			}
		}

		.right {
			display: flex;
			font-size: 28rpx;

			.btn {
				line-height: 66rpx;
				padding: 0 30rpx;
				margin-right: 15rpx;
				border-radius: 36rpx;
				color: #ffffff;
			}

			.cart {
				background-color: #ed3f14;
				margin-right: 30rpx;
			}

			.buy {
				background-color: #ff7900;
			}
		}
	}
</style>
<style scoped lang="scss">
	.mycard1 {
		width: 92%;
		height: 330rpx;
		background-color: #ffffff;
		border-radius: 35rpx;
		margin: 30rpx;
		padding: 10rpx;
	}

	.mycard2 {
		width: 92%;
		height: 100rpx;
		background-color: #ffffff;
		border-radius: 35rpx;
		margin: 30rpx;
	}

	.mysection2 {
		width: 92%;
		line-height: 100rpx;
		margin-top: 30rpx;
		margin-right: 30rpx;
		margin-left: 30rpx;
	}

	page {
		background-color: #f4f4f5;
	}

	.u-card-wrap {
		background-color: $u-bg-color;
		padding: 1px;
	}

	.u-body-item {
		font-size: 32rpx;
		color: #333;
		padding: 20rpx 10rpx;
	}

	.u-body-item image {
		width: 120rpx;
		flex: 0 0 120rpx;
		height: 120rpx;
		border-radius: 8rpx;
		margin-left: 12rpx;
	}

	.u-body-item-price {
		color: #ed3f14;
		font-size: 35rpx;
		margin-right: 15rpx;
		font-weight: bold;
	}
</style>
