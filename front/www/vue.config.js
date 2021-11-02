/**
 * @type {import('@vue/cli-service').ProjectOptions}
 */
module.exports = {
    devServer: {
        proxy: 'http://host.docker.internal:8085'
    }
}