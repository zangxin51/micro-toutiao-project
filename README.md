# micro-toutiao-project

 javaweb练习项目视频[地址](https://www.bilibili.com/video/BV1UN411x7xe)

## 1.前端服务

### 依赖:

node.js : v18.16.0

npm 9.6.6

vue3

vite

vue-router

axios

 pinia

 element-plus

### 启动:  

进入toutiao-static-resouces目录下,在终端执行 npm run dev

在vite.config.js中可以配置后端服务的地址:

```javascript
export default defineConfig(
  () => {
    return{
      plugins: [vue()],
    server: {
      port: 8001,// 前端启动端口: http:localhost:8001
      open: true,
      proxy: {
        '/app-dev': {
          target: 'http://localhost:80/', // 后端服务地址
          changeOrigin: true,
          rewrite: (path) => path.replace(/^\/app-dev/, '')
        }
      }
    }
    }
  }
 )
```

## 2.后端服务

### 依赖:

jdk17及以上版本

tomcat 10

mysql

idea

### 启动: 

#### 1.导入mysql数据库脚本: resources/top_news.sql

#### 2.设置项目根路径为/

#### 3.启动tomcat

