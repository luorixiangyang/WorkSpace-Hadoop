<template>
<div>
    <blog-header></blog-header>
    <hr/>
    <div>
        这是首页，嘻嘻嘻。
    </div>
    <hr/>
    <blog-footer></blog-footer>
</div>
</template>

<script>
import blogHeader from '@/components/common/Header.vue'
import blogFooter from '@/components/common/Footer.vue'

export default {
    name: 'MainIndex',
    // blogHeader/blogFooter组件给申明到components里面然后在template里面使用
    components: {
        blogHeader,
        blogFooter
    }
}
</script>