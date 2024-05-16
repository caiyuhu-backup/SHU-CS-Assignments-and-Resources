<template>
	<view class="comment">
		<u-navbar back-text="返回" title="写评论" :background="background">
			<view class="slot-wrap" slot='right' @click="submitComment">
				<u-icon name='moments'></u-icon>发布
			</view>
		</u-navbar>
		<u-rate :count="5" v-model="rate" :min-count="1" :colors="colors" :icons="icons" inactive-icon="thumb-up"></u-rate>
		<u-input v-model="comment" type="textarea" :heigth='320' placeholder='请输入您高贵的评价...' :focus='true' maxlength='200'/>
		<view class="wrap">
			<view class="pre-box" v-if="!showUploadList">
				<view class="pre-item" v-for="(item, index) in lists" :key="index">
					<image class="pre-item-image" :src="item.url" mode="aspectFit"></image>
				</view>
			</view>
			<u-upload :custom-btn="true" ref="uUpload" :show-upload-list="showUploadList" @on-uploaded='onUploaded' @on-choose-complete='onChoose' :action="action" max-count='9'> 
				<view slot="addBtn" class="slot-btn" hover-class="slot-btn__hover" hover-stay-time="150">
					<u-icon name="photo" size="80" color="#c0c4cc"></u-icon>
				</view>
			</u-upload>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				background: {
					// 渐变色
					background: '#fdc830',  /* fallback for old browsers */      
					background: '-webkit-linear-gradient(to right, rgb(253, 200, 48), rgb(243, 115, 53))',  /* Chrome 10-25, Safari 5.1-6 */      
					background: 'linear-gradient(to right, rgb(253, 200, 48), rgb(243, 115, 53))' /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */      
				},
				rate: 0,
				colors: ['#ffc454', '#ff9500', '#FA3534'],
				icons: ['thumb-down-fill', 'thumb-down-fill', 'thumb-up-fill', 'thumb-up-fill'],
				comment: '',
				action: 'http://upload.itbear666.top:8081/upload/singleFile',
				showUploadList: false, 
				lists: [],
				orderId: ''
			}
		},
		methods: {
			async submitComment() {
				if (this.isChoose && !this.isuploaded) {
					this.$u.toast('请等待图片上传完毕~')
					return
				}
				await uni.showLoading({
					title: '加载中...'
				})
				console.log('发布')
				console.log({
					orderId: this.orderId,
					text: this.comment,
					grade: this.rate,
					imgList: this.imgList
				})
				const res = await this.$u.api.submitComment({
					orderId: this.orderId,
					text: this.comment,
					grade: this.rate,
					imgList: this.imgList
				})
				
				await this.$u.toast('评价发布成功~')
				uni.hideLoading()
				this.$u.route({
					url: 'pages/orders/orders',
					type: 'tab'
				})
			}, 
			onUploaded(lists, name) {
				console.log(lists, name)
				if (lists.length > 0) {
					this.isuploaded = true
					this.isChoose = false
					this.$u.toast('图片上传成功~')
				} else {
					this.$u.toast('图片上传失败！')
				}
			},
			onChoose() {
				this.isChoose = true
			}
		},
		computed: {
			imgList() {
				let imgList =  []
				this.lists.forEach((item)=>{
					imgList.push(item.response.data.url)
				})
				return imgList
			}
		},
		// 只有onReady生命周期才能调用refs操作组件
		onReady() {
			// 得到整个组件对象，内部图片列表变量为"lists"
			this.lists = this.$refs.uUpload.lists
			console.log('lists', this.lists);
		},
		onLoad(options) {
			this.orderId = options.orderId
			console.log('orderId', options.orderId);
		}
	}
</script>

<style scoped>
	page {
		background: #fff;
	}
	.comment {
		margin: 30rpx;
	}
	.slot-wrap {
		flex: 1;
		padding: 0 30rpx;
		font-weight: bold;
		color: #f4f4f5;
	}
	
	.wrap {
		margin-top: 30rpx;
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
		width: 50%;
		height: 240rpx;
	}
</style>
