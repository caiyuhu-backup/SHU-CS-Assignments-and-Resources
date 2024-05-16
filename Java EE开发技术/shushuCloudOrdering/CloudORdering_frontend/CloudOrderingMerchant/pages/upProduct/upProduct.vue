<template>
	<view class='center-card'>
		<u-form :model="form" ref="uForm" label-width='150' :label-style='labelStyle' 
		class='u-m-l-18' :errory-type='["toast"]'>
			<u-form-item label="分类" prop='categoryName'>
				<u-input v-model="form.categoryName" />
				<u-button style='width: 200rpx;' size='mini' @click='showSelect = true'>从现有分类中选择</u-button>
			</u-form-item>
			<u-form-item label="名称" prop='productName'>
				<u-input v-model="form.productName" />
			</u-form-item>
			<u-form-item label="价格">
				<u-number-box class='u-m-r-12' v-model="form.sellPrice" :positive-integer='false' :min='0.01' input-width='200'></u-number-box>
				元
			</u-form-item>
			<u-form-item label="库存">
				<u-number-box class='u-m-r-12' v-model="form.stock" input-width='200' :min='1'></u-number-box>
				个/份
			</u-form-item>
			<u-form-item label="状态">
				<view v-show="switchVal" style="color: #606266;">上架</view>
				<view v-show="!switchVal" style="color: #fa3534;">下架</view>
				<u-switch slot="right" v-model="switchVal" active-color="#f29100" inactive-color="#dee1e6" ></u-switch>
			</u-form-item>
			<u-form-item label="图片">
				<view class="wrap">
					<u-upload :custom-btn="true" ref="uUpload" :action="action" max-count='1' @on-uploaded='onUploaded'> 
						<view slot="addBtn" class="slot-btn" hover-class="slot-btn__hover" hover-stay-time="150">
							<u-icon name="photo" size="80" color="#c0c4cc"></u-icon>
						</view>
					</u-upload>
				</view>
			</u-form-item>
			<u-button class='u-m-t-50' type='warning' @click='submit' style='width: 300rpx;'>点击提交</u-button>
		</u-form>
		<u-select v-model="showSelect" :list="categoryList" @confirm='confirmCategoryName'></u-select>
		<u-modal v-model="showModal" :show-title='false' :show-confirm-button='false' width="26%" :mask-close-able='true'>
			<u-circle-progress active-color="#18b566" :percent="100" style='text-align: center;'>
				<view>正在提交</view>
			</u-circle-progress>
		</u-modal>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				showModal: false,
				form: {
					productName: '',
					productImg: '',
					sellPrice: 0.01,
					stock: 1
				},
				rules: {
					categoryName: [
						{
							required: true,
							message: '请输入分类名称',
							trigger: ['blur']
						}
					],productName: [
						{
							required: true,
							message: '请输入商品名称',
							trigger: ['blur']
						}
					]
				},
				switchVal: true,
				action: 'http://upload.itbear666.top:8081/upload/singleFile',
				isuploaded: false,
				imgUrl: '',
				labelStyle: {
					'color': '#ff9901',
					'fontWeight': 'bold',
					'fontSize': '32rpx'
				},
				showSelect: false,
				categoryList: [],
			}
		},
		onReady() {
			this.$refs.uForm.setRules(this.rules)
		},
		onLoad() {
			this.getCategoryList()
		},
		methods: {
			async getCategoryList() {
				const res = await this.$u.api.getCategoryList(this.vuex_merchant_user.vendorId)
				res.forEach((item, index)=>{
					this.categoryList.push({
						value: index,
						label: item
					})
				})
			},
			confirmCategoryName(e) {
				this.form.categoryName = e[0].label
			},
			async submit() {
				this.$refs.uForm.validate(async valid => {
					if (valid) {
						// 上传图片
						if (this.imgUrl === '') {
							this.$u.toast('请上传图片，如已提交则请等待上传完毕！')
							return
						}
						console.log('验证通过')
						this.showModal = true
						const params = {
							vendorId: this.vuex_merchant_user.vendorId,
							categoryName: this.form.categoryName,
							productVendorVoList: [
								{
									productName: this.form.productName,
									productImg: this.imgUrl,
									sellPrice: this.form.sellPrice,
									stock: this.form.stock
								},
							]
						}
						console.log(params)
						const res = await this.$u.api.addProduct(params)
						setTimeout(()=>{
							this.showModal = false
							this.imgUrl = ''
							this.$u.route({
								url: 'pages/productList/productList',
								type: 'tab'
							})
						}, 1800)
					} else {
						console.log('验证失败')
					}
				})
			},
			onUploaded(lists, name) {
				console.log(lists, name);
				try {
					if (lists.length > 0) {
						this.imgUrl = lists[0].response.data.url
					}
					this.isuploaded = true
					this.$u.toast('图片上传成功~')
				} catch(e) {
					console.log(e.message);
					this.$u.toast('图片上传失败！')
				}
			}
		}
	}
</script>

<style lang="scss" scoped>
	.center-card {
		height: 1100rpx;
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
</style>
