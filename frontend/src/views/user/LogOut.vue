<template>
</template>

<script>
export default {
mounted () {
    this.$store.dispatch('auth/logout').then(
        this.$router.replace({
        path: '/login'
    })
    )
}
}
</script>
