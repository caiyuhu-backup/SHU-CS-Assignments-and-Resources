<template>
	<view style="margin: 18rpx;">
		<u-top-tips ref="uTips"></u-top-tips>
		<u-row gutter="16" class='center-card'>
			<u-col span="4">
				<u-avatar :src="vendorInfo === null ? '' : vendorInfo.vendorImg"
					class="u-padding-10" size="220"></u-avatar>
			</u-col>
			<u-col span="8">
				<view class="vendor-name u-line-2">
					{{vendorInfo === null ? '' : vendorInfo.vendorName}}
				</view>
				<view class="u-flex u-m-t-15 u-font-32">
					<view class="vendor-score">
						<u-icon name="star-fill"></u-icon>{{vendorInfo.score === null ? 0 : vendorInfo.score}}
					</view>
					<view class="u-m-l-12" style="color: #82848a;">
						已售
						<u-count-to :start-val="0" :end-val="vendorInfo.totalSold" :font-size="32"></u-count-to>
						单
					</view>
					<text class="u-m-l-12" style="color: #fa3534;">￥{{vendorInfo.averagePrice}}/人</text>
				</view>
				<view class="u-line-2 u-m-t-15" style="color: #82848a;">
					公告：{{vendorInfo === null ? '' : vendorInfo.announcement}}
				</view>
			</u-col>
		</u-row>
		<!-- border-radius: 35rpx; -->
		<u-cell-group>
			<u-cell-item :arrow="false" icon="integral-fill" title="营业" :value="inBusiness === true ? '营业中' : '打烊了'">
				<u-icon slot="icon" size="32" name="search" class='u-m-r-12'></u-icon>
				<u-switch slot="right-icon" v-model="inBusiness" active-color="#f29100" inactive-color="#dee1e6" 
				@change='updateIsBusiness'></u-switch>
			</u-cell-item>
			<u-cell-item icon="setting-fill" title="修改信息" @click='toChange'></u-cell-item>
		</u-cell-group>
		<u-modal v-model="showModal" :show-title='false' :show-confirm-button='false' width="26%" :mask-close-able='true'>
			<u-circle-progress active-color="#18b566" :percent="100" style='text-align: center;'>
				<view class="u-progress-content">
					<view class="u-progress-dot"></view>
					<text class='u-progress-info'>操作中</text>
				</view>
			</u-circle-progress>
		</u-modal>
		
		<u-grid :col="2">
			<u-grid-item @click='scanCode'>
				<u-icon name="scan" :size="46"></u-icon>
				<view class="grid-text">扫码核销</view>
			</u-grid-item>
			<u-grid-item @click='toUpProduct'>
				<u-icon name="order" :size="46"></u-icon>
				<view class="grid-text">上架商品</view>
			</u-grid-item>
		</u-grid>
	</view>
</template>

<script>
	let Qrcode = require('../../common/reqrcode.js')
	export default {
		data() {
			return {
				qrCodeRes: '',
				vendorInfo: {},
				inBusiness: false,
				showModal: false
			}
		},
		async onShow() {
			await this.getUserInfo()
			await this.getVendorInfo()
		},
		onLoad() {
			
		},
		onPullDownRefresh() {
			this.getUserInfo()
			this.getVendorInfo()
			setTimeout(function () {
				uni.stopPullDownRefresh()
			}, 600);
		},
		computed: {
		},
		methods: {
			toUpProduct() {
				this.$u.route({
					url: 'pages/upProduct/upProduct',
					params: {vendorId: this.vuex_merchant_user.vendorId}
				})
			},
			// 前往修改设置页面
			toChange() {
				const {vendorImg, vendorName, announcement} = this.vendorInfo
				this.$u.route({
					url: 'pages/change/change',
					params: {vendorImg, announcement, vendorName}
				})
			},
			async updateIsBusiness(value) {
				this.showModal = true
				await this.updateVendorInfo({vendorId: this.vuex_merchant_user.vendorId, inBusiness: value === true ? 1 : 2})	
				await this.getVendorInfo()
				setTimeout(()=>{
					this.showModal = false
					this.$refs.uTips.show({
						title: '修改成功！',
						type: 'success',
						duration: '1300'
					})
				}, 1800)
			},
			async updateVendorInfo(params) {
				/**
				 *  {
						vendorId: 1,
						vendorName: "卫龙辣条加工厂",
						vendorImg: "https://tse1-mm.cn.bing.net/th/id/R-C.285f8ab5e37a2921937695aa40b8f2a5?rik=hKwpVsZD5PisNw&riu=http%3a%2f%2fpic.ntimg.cn%2ffile%2f20200510%2f25058003_093911362087_2.jpg&ehk=UkTa1X3uLfVk2gWvV%2b7Or4q4geNRLqHVN%2buPwWoEsWk%3d&risl=&pid=ImgRaw&r=0",
						announcement: "难吃的一匹11",
						inBusiness: 1	
					}
				 */
				const res = await this.$u.api.updateVendorInfo(params)
			},
			async getUserInfo() {
				const userInfo = await this.$u.api.getUserInfo()
				// console.log(userInfo)
				if (this.$u.test.isEmpty(userInfo)) {
					this.toLoginPage()
					return
				}
				this.$u.vuex('vuex_merchant_user', userInfo)
			},
			toLoginPage() {
				this.$u.route({
					url: 'pages/login/login',
				})
			},
			async getVendorInfo() {
				if (this.$u.test.isEmpty(this.vuex_merchant_user.vendorId)) {
					return
				}
				this.vendorInfo = await this.$u.api.getVendorBriefInfo(this.vuex_merchant_user.vendorId)
				this.inBusiness = this.vendorInfo.inBusiness === 1 ? true : false
				console.log(this.vendorInfo)	
			},
			async checkOrderCode() {
				const res = await this.$u.api.checkQrCode(this.qrCodeRes)
				this.$refs.uTips.show({
					title: '订单核销成功！',
					type: 'success',
					duration: '2300'
				})
			},
			// 扫码
			scanCode() {
				// #ifdef APP-PLUS
				this.scanCodeAPP()
				// #endif
				
				// #ifdef H5
				this.scanCodeH5()
				// #endif
			},
			// APP直接调用 uni.scanCode 接口
			scanCodeAPP() {
				uni.scanCode({
					scanType: ['qrCode'],
					success: (res) => {
						this.showModal = true
						this.qrCodeRes = res.result
						setTimeout(()=>{
							this.showModal = false
							this.$refs.uTips.show({
								title: '扫码成功！',
								type: 'success',
								duration: '1300'
							})
							this.checkOrderCode()
						}, 2000)
						
					}
				})
			},
			// H5通过拉起相机拍照来识别二维码
			scanCodeH5() {
				uni.chooseImage({
					count: 1,
					success: imgRes => {
						Qrcode.qrcode.decode(this.getObjectURL(imgRes.tempFiles[0]))
						Qrcode.qrcode.callback = (codeRes) => {
							if (codeRes.indexOf('error') >= 0) {
								// 二维码识别失败
								this.showModal = true
								// this.qrCodeRes = '不合法二维码：' + codeRes
								setTimeout(()=>{
									this.showModal = false
									this.$refs.uTips.show({
										title: '二维码识别失败！',
										type: 'error',
										duration: '1300'
									})
								}, 1000)
							} else {
								this.showModal = true
								// 二维码识别成功
								let r = this.decodeStr(codeRes)
								setTimeout(()=>{
									this.showModal = false
									this.$refs.uTips.show({
										title: '二维码识别成功！',
										type: 'success',
										duration: '1300'
									})
									this.qrCodeRes = r
									this.checkOrderCode()
								}, 2000)
								
							}
						}
					}
				})
			},
			// 获取文件地址函数
			getObjectURL(file) {
				var url = null
				if (window.createObjectURL !== undefined) { // basic
					url = window.createObjectURL(file)
				} else if (window.URL !== undefined) { // mozilla(firefox)
					url = window.URL.createObjectURL(file)
				} else if (window.webkitURL !== undefined) { // webkit or chrome
					url = window.webkitURL.createObjectURL(file)
				}
				return url
			},
			// 解码，输出：中文
			decodeStr(str) {
				var out, i, len, c;
				var char2, char3;
				out = "";
				len = str.length;
				i = 0;
				while (i < len) {
					c = str.charCodeAt(i++);
					switch (c >> 4) {
						case 0:
						case 1:
						case 2:
						case 3:
						case 4:
						case 5:
						case 6:
						case 7:
							// 0xxxxxxx
							out += str.charAt(i - 1);
							break;
						case 12:
						case 13:
							// 110x xxxx 10xx xxxx
							char2 = str.charCodeAt(i++);
							out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
							break;
						case 14:
							// 1110 xxxx 10xx xxxx 10xx xxxx
							char2 = str.charCodeAt(i++);
							char3 = str.charCodeAt(i++);
							out += String.fromCharCode(((c & 0x0F) << 12) |
								((char2 & 0x3F) << 6) |
								((char3 & 0x3F) << 0));
							break;
					}
				}
				return out;
			},
		},
		watch: {
			
		}
	}
</script>

<style>
	/* page不能写带scope的style标签中，否则无效 */
	page {
		background-color: #f4f4f5;
	}
</style>

<style lang="scss" scoped>
	.u-row {
		margin: 18rpx 0;
	}
	
	.vendor-name {
		font-size: 50rpx; 
		color: #303133; 
		font-weight: bold;
	}
	
	.vendor-score {
		color: #f29100; 
	}
	
	.center-card {
		width: 100%;
		height: 300rpx;
		background-color: #ffffff;
		border-radius: 35rpx;
		margin-bottom: 30rpx;
		padding: 10rpx;
	}
	
	.u-progress-content {
		display: flex;
		align-items: center;
		justify-content: center;
	}
	
	.u-progress-dot {
		width: 16rpx;
		height: 16rpx;
		border-radius: 50%;
		background-color: #fb9126;
	}
	
	.u-progress-info {
		font-size: 28rpx;
		padding-left: 16rpx;
		letter-spacing: 2rpx
	}
</style>
