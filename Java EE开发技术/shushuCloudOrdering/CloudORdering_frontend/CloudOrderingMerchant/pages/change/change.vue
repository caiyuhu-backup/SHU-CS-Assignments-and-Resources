<template>
	<view class='center-card'>
		<u-form :model="form" ref="uForm" label-width='150' :label-style='labelStyle' class='u-m-l-18'>
			<u-form-item label="商店名称"><u-input v-model="form.vendorName" /></u-form-item>
			<u-form-item label="公告栏"><u-input v-model="form.announcement" /></u-form-item>
			<u-form-item label="商家图片">
				<view class="wrap">
					<u-upload :custom-btn="true" ref="uUpload" :action="action" max-count='1' @on-uploaded='onUploaded' @on-choose-complete='onChoose'> 
						<view slot="addBtn" class="slot-btn" hover-class="slot-btn__hover" hover-stay-time="150">
							<u-icon name="photo" size="80" color="#c0c4cc"></u-icon>
						</view>
					</u-upload>
				</view>
			</u-form-item>
			
			<u-button class='u-m-t-50' type='warning' @click='submit' style='width: 300rpx;'>点击提交</u-button>
		</u-form>
		<u-modal v-model="showModal" :show-title='false' :show-confirm-button='false' width="26%" :mask-close-able='true'>
			<u-circle-progress active-color="#18b566" :percent="100" style='text-align: center;'>
				<view>正在修改</view>
			</u-circle-progress>
		</u-modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				form: {
					vendorName: '',
					announcement: ''
				},
				action: 'http://upload.itbear666.top:8081/upload/singleFile',
				labelStyle: {
					'color': '#ff9901',
					'fontWeight': 'bold',
					'fontSize': '32rpx'
				},
				showModal: false,
				isuploaded: false,
				isChoose: false,
				imgUrl: ''
			}
		},
		onLoad(options) {
			console.log(options);
			this.form.vendorName = options.vendorName
			this.form.announcement = options.announcement
		},
		methods: {
			async submit() {
				if (this.isChoose && this.imgUrl === '') {
					this.$u.toast('请等待图片上传完毕！')
					return
				}
				this.showModal = true
				const res = await this.$u.api.updateVendorInfo({
					vendorId: this.vuex_merchant_user.vendorId, 
					vendorImg: this.imgUrl,
					vendorName: this.form.vendorName,
					announcement: this.form.announcement
				})
				setTimeout(()=>{
					this.showModal = false
					this.imgUrl = ''
					this.$u.route({
						url: 'pages/index/index',
						type: 'tab'
					})
				}, 1800)
			},
			onUploaded(lists, name) {
				console.log(lists, name)
				try {
					if (lists.length > 0) {
						this.imgUrl = lists[0].response.data.url
					}
					this.isuploaded = true
					this.$u.toast('图片上传成功~')
				} catch(e) {
					this.$u.toast('图片上传失败！')
				}
			},
			onChoose() {
				this.isChoose = true
			}
		},
		onReady() {
			this.lists = this.$refs.uUpload.lists
		}
	}
</script>

<style scoped>
	.center-card {
		height: 700rpx;
		background-color: #ffffff;
		border-radius: 35rpx;
		margin: 18rpx;
		padding: 10rpx;
	}
	.wrap {
		padding: 24rpx;
	}
	
	.slot-btn {
		width: 341rpx;
		height: 240rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		background: rgb(244, 245, 246);
		border-radius: 10rpx;
	}

	.slot-btn__hover {
		background-color: rgb(235, 236, 238);
	}

	.pre-box {
		display: flex;
		align-items: center;
		justify-content: space-between;
		flex-wrap: wrap;
	}

	.pre-item {
		flex: 0 0 48.5%;
		border-radius: 10rpx;
		height: 240rpx;
		overflow: hidden;
		position: relative;
		margin-bottom: 20rpx;
	}

	.pre-item-image {
		width: 100%;
		height: 240rpx;
	}
</style>
