<template>
	<view>
		<u-navbar> 
			<u-search placeholder="校园美食尽在掌握" v-model="keyword" 
				@custom="toSearchDetail" @search="toSearchDetail" 
				height=72 focus style="width: 650rpx;">
			</u-search>
		</u-navbar>
		<u-popup v-model="showPopup" mode="center" border-radius="14" width="500" height="200">
			<view class="popup_content">
				<view class="u-font-100">
					确认清空搜索记录？
				</view>
				<u-button @click="showPopup = false" size="medium" class="u-m-t-40 u-m-r-10" :ripple="true">取消</u-button>
				<u-button @click="rmKeywords" size="medium" type="warning" :ripple="true">确定</u-button>
			</view>
		</u-popup>
		<view class="u-m-t-25 u-m-l-18 u-m-r-18">
			<u-section title="历史搜索" class="u-m-t-15 u-m-b-10" font-size=30>
				<template slot="right">
					<u-icon name="trash" size="35" @click="toRmKeywords"></u-icon>
				</template>
			</u-section>
			<view class="u-line-5 u-m-t-23">
				<u-button v-for="(word, index) in searchKeywords" :key="index" 
				:custom-style="customButtonStyle" shape="circle"
				size="mini" hover-class="none" @click="toSearchDetailByCurrentKeyWord(word)">{{word}}</u-button>
			</view>
			<u-empty v-show="false" text="数据为空" mode="list" icon-size=80></u-empty>
			<u-section title="热门搜索" class="u-m-t-35 u-m-b-10" font-size=30>
				<template slot="right">
					换一换<u-icon name="reload" size="35"></u-icon>
				</template>
			</u-section>
			<u-empty text="暂无" mode="list" icon-size=80></u-empty>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				keyword: '',
				showPopup: false,
				customButtonStyle: {
					margin: '3px', // 注意驼峰命名，并且值必须用引号包括，因为这是对象，
					backgroundColor: '#f5f7f9',
					color: '#1a1a1c'
				}
			}
		},
		methods: {
			toSearchDetail() {
				if (this.$u.test.isEmpty(this.keyword)) {
					this.$u.toast('输入框不能为空~')
				} else {
					// TODO 跳转到搜索详情界面
					if (this.searchKeywords.indexOf(this.keyword) === -1) {
						this.searchKeywords.push(this.keyword)
						this.$u.vuex('searchKeywords', this.searchKeywords)
					}
					this.$u.route({
						url: 'pages/searchDetail/searchDetail',
						params: {
							keyword: this.keyword
						}
					})
					this.keyword = ''
				}
			},
			toSearchDetailByCurrentKeyWord(word) {
				this.$u.route({
					url: 'pages/searchDetail/searchDetail',
					params: {
						keyword: word
					}
				})
			},
			toRmKeywords() {
				this.showPopup = true
			},
			rmKeywords() {
				if (!this.$u.test.isEmpty(this.searchKeywords)) {
					this.$u.vuex('searchKeywords', [])
				}
				this.showPopup = false
				this.$u.toast('清空完成~')
			}
		}
	}
</script>

<style scoped lang="scss">
	.popup_content {
		padding: 24rpx;
		text-align: center;
	}
</style>
