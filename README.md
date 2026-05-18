# MyPetStore-CMS 宠物商店后台管理系统

<div align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.4-brightgreen?style=flat-square&logo=spring-boot)
![MyBatis-Plus](https://img.shields.io/badge/MyBatis--Plus-3.5.5-blue?style=flat-square)
![Maven](https://img.shields.io/badge/Maven-3.6+-red?style=flat-square&logo=apache-maven)
![License](https://img.shields.io/badge/License-MIT-yellow?style=flat-square)

**基于 Spring Boot 3 + MyBatis-Plus 的多模块宠物商店后台管理系统**

</div>

---

## 📖 项目简介

MyPetStore-CMS 是一个功能完善的宠物商店后台内容管理系统（Content Management System），采用现代化的多模块架构设计，提供用户管理、商品目录管理、订单管理等核心功能。

### ✨ 主要特性

- 🏗️ **多模块架构**：清晰的模块划分，职责分离，易于维护和扩展
- 🔐 **安全认证**：基于 Session 的管理员登录认证机制
- 👥 **用户管理**：支持用户查询、详情查看、信息编辑、密码重置等功能
- 📦 **商品管理**：完整的商品分类、商品、SKU（Item）的 CRUD 操作
- 🛒 **订单管理**：订单查询、详情查看、状态修改、发货处理、订单删除等
- 🔍 **搜索功能**：支持按关键词搜索用户、订单、商品等
- 🎨 **友好界面**：基于 Thymeleaf 模板引擎的响应式管理界面
- 📊 **数据持久化**：使用 MyBatis-Plus 简化数据库操作，支持逻辑删除
- 🛠️ **统一异常处理**：全局异常处理器，标准化错误响应
- 🧰 **通用工具类**：丰富的工具类库（字符串、日期、加密、验证等）

---

## 🏗️ 技术栈

### 后端技术
- **核心框架**：Spring Boot 3.4.4
- **ORM 框架**：MyBatis-Plus 3.5.5
- **数据库**：MySQL 8.0+
- **模板引擎**：Thymeleaf
- **构建工具**：Maven 3.6+
- **JDK 版本**：Java 21

### 前端技术
- **模板引擎**：Thymeleaf
- **CSS 框架**：自定义 CSS（admin.css）
- **JavaScript**：原生 JavaScript

### 开发工具
- **IDE**：IntelliJ IDEA（推荐）
- **数据库工具**：Navicat / MySQL Workbench
- **API 测试**：Postman / Browser

---

## 📁 项目结构

```
MyPetStore-CMS/
├── store-common/              # 通用模块（工具类、常量、异常处理等）
│   ├── src/main/java/org/csu/petstore/common/
│   │   ├── handler/           # 全局异常处理器
│   │   ├── exception/         # 自定义异常类
│   │   ├── util/              # 工具类（StringUtil, DateUtil, Md5Util等）
│   │   ├── dto/               # 数据传输对象（PageQuery, PageResult）
│   │   ├── Constants.java     # 常量定义
│   │   └── Result.java        # 统一响应结果
│   └── pom.xml
│
├── store-model/               # 实体模型模块
│   ├── src/main/java/org/csu/petstore/
│   │   ├── entity/            # 数据库实体类（Account, Orders, Product等）
│   │   └── Vo/                # 视图对象（CategoryVO, ProductVO, ItemVO等）
│   └── pom.xml
│
├── store-dao/                 # 数据访问层模块
│   ├── src/main/java/org/csu/petstore/persistence/
│   │   ├── AccountMapper.java      # 账户 Mapper
│   │   ├── OrdersMapper.java       # 订单 Mapper
│   │   ├── CategoryMapper.java     # 分类 Mapper
│   │   ├── ProductMapper.java      # 商品 Mapper
│   │   ├── ItemMapper.java         # SKU Mapper
│   │   └── ...                     # 其他 Mapper
│   └── pom.xml
│
├── store-service/             # 业务逻辑层模块
│   ├── src/main/java/org/csu/petstore/service/
│   │   ├── AccountService.java     # 账户服务接口
│   │   ├── OrderService.java       # 订单服务接口
│   │   ├── CatalogService.java     # 商品目录服务接口
│   │   └── impl/                   # 服务实现类
│   └── pom.xml
│
├── store-admin-web/           # Web 管理端模块（启动入口）
│   ├── src/main/java/org/csu/petstore/
│   │   ├── controller/         # 控制器（Account, User, Order, Catalog）
│   │   └── MyPetStoreCMSApplication.java  # 启动类
│   ├── src/main/resources/
│   │   ├── templates/admin/    # Thymeleaf 模板页面
│   │   │   ├── account/        # 账户相关页面
│   │   │   ├── user/           # 用户管理页面
│   │   │   ├── order/          # 订单管理页面
│   │   │   ├── catalog/        # 商品目录页面
│   │   │   └── common/         # 公共组件（顶部导航等）
│   │   ├── static/css/         # 静态资源（CSS）
│   │   └── application.yml     # 应用配置文件
│   └── pom.xml
│
├── pom.xml                    # 父 POM 文件
├── .gitignore                 # Git 忽略配置
└── README.md                  # 项目说明文档
```

---

## 🚀 快速开始

### 前置要求

- JDK 21 或更高版本
- Maven 3.6+
- MySQL 8.0+
- IntelliJ IDEA（推荐）或 Eclipse

### 安装步骤

#### 1. 克隆项目

```bash
git clone https://github.com/ChuMochou/MyPetStore-CMS.git
cd MyPetStore-CMS
```

#### 2. 创建数据库

在 MySQL 中创建数据库 `mypetstore`，并导入 SQL 脚本。

```sql
CREATE DATABASE mypetstore DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE mypetstore;
-- 导入 SQL 脚本
SOURCE /path/to/mypetstore.sql;
```

#### 3. 配置数据库连接

修改 `store-admin-web/src/main/resources/application.yml` 文件中的数据库配置：

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mypetstore?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai
    username: root        # 修改为你的数据库用户名
    password: 123456      # 修改为你的数据库密码
```

#### 4. 编译项目

在项目根目录执行：

```bash
mvn clean install
```

#### 5. 启动应用

方式一：使用 Maven 启动
```bash
cd store-admin-web
mvn spring-boot:run
```

方式二：使用 IDE 启动
- 打开 `store-admin-web/src/main/java/org/csu/petstore/MyPetStoreCMSApplication.java`
- 右键点击 → Run 'MyPetStoreCMSApplication'

#### 6. 访问系统

浏览器访问：http://localhost:8080

默认管理员账号：
- 用户名：admin
- 密码：123456

---

## 📋 功能模块

### 1. 账户管理 (Account)
- ✅ 管理员登录/登出
- ✅ 新用户注册
- ✅ Session 会话管理

**访问路径**：`/account/*`

### 2. 用户管理 (User)
- ✅ 查看所有用户列表
- ✅ 按关键词搜索用户（用户名、姓名、邮箱）
- ✅ 查看用户详细信息
- ✅ 编辑用户信息（姓名、邮箱、电话、地址等）
- ✅ 重置用户密码
- ✅ 用户状态管理

**访问路径**：`/user/*`

### 3. 商品目录管理 (Catalog)
- ✅ 商品分类管理（Category）
  - 查看全部分类
  - 添加/编辑/删除分类
  - 搜索分类
  
- ✅ 商品管理（Product）
  - 查看分类下的商品
  - 添加/编辑/删除商品
  - 搜索商品
  
- ✅ SKU 管理（Item）
  - 查看商品下的 SKU
  - 添加/编辑/删除 SKU
  - 设置价格、库存、属性等

**访问路径**：`/catalog/*`

### 4. 订单管理 (Order)
- ✅ 查看所有订单列表
- ✅ 按用户 ID 筛选订单
- ✅ 按关键词搜索订单（订单号、用户 ID、客户姓名）
- ✅ 查看订单详细信息
  - 订单基本信息
  - 收货/账单地址
  - 订单项列表
  - 订单状态
- ✅ 编辑订单信息
- ✅ 修改订单状态（待付款、已付款、已发货、已完成等）
- ✅ 发货处理
- ✅ 删除订单（逻辑删除）

**访问路径**：`/order/*`

---

## ⚙️ 配置说明

### 应用配置 (application.yml)

```yaml
server:
  port: 8080                    # 服务端口
  servlet:
    context-path: /             # 上下文路径

spring:
  application:
    name: store-admin
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mypetstore
    username: root
    password: 123456
  
  thymeleaf:
    cache: false                # 开发时关闭缓存，便于调试
  
mybatis-plus:
  mapper-locations: classpath*:/mapper/**/*.xml
  type-aliases-package: org.csu.petstore.entity
  configuration:
    map-underscore-to-camel-case: true   # 下划线转驼峰
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl  # SQL 日志
  global-config:
    db-config:
      id-type: auto                      # 主键自增
      logic-delete-field: delFlag        # 逻辑删除字段
      logic-delete-value: 1
      logic-not-delete-value: 0
```

### 模块依赖关系

```
store-admin-web (Web层)
    ↓ 依赖
store-service (业务层)
    ↓ 依赖
store-dao (数据访问层)
    ↓ 依赖
store-model (实体层)
    ↓ 依赖
store-common (通用层)
```

所有模块都依赖于 `store-common`，它提供了项目中共享的工具类和基础设施。

---

## 🛠️ 开发指南

### 添加新功能

1. **在 store-model 中创建实体类**
   ```java
   @Data
   @TableName("your_table")
   public class YourEntity {
       @TableId(type = IdType.AUTO)
       private Long id;
       // 其他字段...
   }
   ```

2. **在 store-dao 中创建 Mapper 接口**
   ```java
   @Mapper
   public interface YourMapper extends BaseMapper<YourEntity> {
       // 自定义查询方法...
   }
   ```

3. **在 store-service 中创建服务接口和实现**
   ```java
   public interface YourService {
       // 业务方法...
   }
   
   @Service
   public class YourServiceImpl implements YourService {
       @Autowired
       private YourMapper yourMapper;
       // 实现业务逻辑...
   }
   ```

4. **在 store-admin-web 中创建 Controller**
   ```java
   @Controller
   @RequestMapping("/your-module")
   public class YourController {
       @Autowired
       private YourService yourService;
       
       @GetMapping("/list")
       public String list(Model model) {
           // 处理请求...
           return "admin/your-module/list";
       }
   }
   ```

5. **在 templates/admin 中创建 Thymeleaf 模板**

### 代码规范

- 遵循阿里巴巴 Java 开发手册
- 使用 Lombok 简化代码（@Data, @Slf4j 等）
- 统一使用 `Result<T>` 封装 API 响应
- 使用 `BusinessException` 处理业务异常
- 合理使用日志记录（SLF4J）

---

## 📊 数据库设计

### 核心表结构

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| account | 用户账户表 | userid, password, firstname, lastname, email, phone, status |
| category | 商品分类表 | catid, name, descn |
| product | 商品表 | productid, category, name, descn |
| item | SKU 表 | itemid, productid, listprice, unitcost, supplier, status |
| orders | 订单表 | orderid, userid, orderdate, shipaddr, billaddr, totalprice |
| lineitem | 订单项表 | orderid, linenum, itemid, quantity, unitprice |
| orderstatus | 订单状态表 | orderid, status, timestamp |

---

## 🔧 常见问题

### 1. 启动时提示找不到数据库

**解决方案**：
- 检查 MySQL 是否启动
- 确认数据库 `mypetstore` 已创建
- 检查 `application.yml` 中的数据库配置是否正确

### 2. 登录时提示用户名不存在

**解决方案**：
- 确认数据库中已有管理员账户
- 检查 `account` 表中是否有数据
- 可以使用注册功能创建新账户

### 3. 编译时出现 TypeTag 错误

**解决方案**：
- 确保使用 JDK 21
- 在 IDEA 中：File → Settings → Build → Compiler → Java Compiler，设置 Target bytecode version 为 21
- 重新构建项目：Build → Rebuild Project

### 4. 页面样式加载失败

**解决方案**：
- 检查 `static/css/admin.css` 文件是否存在
- 清除浏览器缓存
- 检查 Thymeleaf 配置中的静态资源路径

---

## 📝 更新日志

### v1.0.0 (2025-05-18)
- ✨ 初始版本发布
- ✅ 实现用户管理功能
- ✅ 实现商品目录管理功能
- ✅ 实现订单管理功能
- ✅ 完善 store-common 模块（全局异常处理、工具类等）
- ✅ 优化 Result 响应格式
- ✅ 改进 DateUtil 使用 Java 8 时间 API
- ✅ 增强 Md5Util 支持 SHA-256 加密
- ✅ 新增 ValidationUtil 验证工具类
- ✅ 新增 ResponseUtil 响应工具类

---

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 开启 Pull Request

---

## 📄 许可证

本项目采用 MIT 许可证 - 详见 [LICENSE](LICENSE) 文件

---

## 👨‍💻 作者

- **Organization**: CSU (中南大学)
- **Project**: MyPetStore-CMS

---

## 📞 联系方式

如有问题或建议，请通过以下方式联系：
- 提交 Issue
- 发送邮件至：[your-email@example.com]

---

<div align="center">

**⭐ 如果这个项目对你有帮助，请给个 Star 支持一下！⭐**

Made with ❤️ by CSU Team

</div>
