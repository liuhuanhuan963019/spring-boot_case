/* eslint-disable quotes */
import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

/* Layout */
import Layout from "@/layout";

/**
 * Note: sub-menu only appear when route children.length >= 1
 * Detail see: https://panjiachen.github.io/vue-element-admin-site/guide/essentials/router-and-nav.html
 *
 * hidden: true                   if set true, item will not show in the sidebar(default is false)
 * alwaysShow: true               if set true, will always show the root menu
 *                                if not set alwaysShow, when item has more than one children route,
 *                                it will becomes nested mode, otherwise not show the root menu
 * redirect: noRedirect           if set noRedirect will no redirect in the breadcrumb
 * name:'router-name'             the name is used by <keep-alive> (must set!!!)
 * meta : {
    roles: ['admin','editor']    control the page roles (you can set multiple roles)
    title: 'title'               the name show in sidebar and breadcrumb (recommend set)
    icon: 'svg-name'/'el-icon-x' the icon show in the sidebar
    breadcrumb: false            if set false, the item will hidden in breadcrumb(default is true)
    activeMenu: '/example/list'  if set path, the sidebar will highlight the path you set
  }
 */

/**
 * constantRoutes
 * a base page that does not have permission requirements
 * all roles can be accessed
 */
export const constantRoutes = [
  {
    path: "/login",
    component: () => import("@/views/login/index"),
    hidden: true
  },

  {
    path: "/404",
    component: () => import("@/views/404"),
    hidden: true
  },
  {
    path: "/",
    component: Layout,
    redirect: "/upload",
    children: [
      {
        path: "/upload",
        name: "文件上传",
        component: () => import("@/views/upload/index"),
        meta: { title: "文件上传", icon: "upload" }
      }
    ]
  },
  // 404 page must be placed at the end !!!
  { path: "*", redirect: "/404", hidden: true }
];

// export const asyncRoutes = [
//   {
//     path: "/list",
//     component: Layout,

//     children: [
//       {
//         path: "/list",
//         name: "list",
//         component: () => import("@/views/list/index"),
//         meta: { title: "文章列表", icon: "link", roles: ["admin"] }
//       }
//     ]
//   },
//   {
//     path: "/detail",
//     component: Layout,
//     hidden: true,
//     children: [
//       {
//         path: "/detail",
//         name: "detail",
//         component: () => import("@/views/detail/index"),
//         meta: { title: "文章详情", icon: "link", roles: ["admin"] }
//       }
//     ]
//   },
//   {
//     path: "/edit",
//     component: Layout,

//     children: [
//       {
//         path: "/edit",
//         name: "edit",
//         component: () => import("@/views/edit/index"),
//         meta: { title: "编辑文章", icon: "link", roles: ["editer", "admin"] }
//       }
//     ]
//   },
//   {
//     path: "external-link",
//     component: Layout,

//     children: [
//       {
//         path: "https://panjiachen.github.io/vue-element-admin-site/#/",
//         meta: { title: "External Link", icon: "link", roles: ["admin"] }
//       }
//     ]
//   }
// ];

const createRouter = () =>
  new Router({
    mode: "hash", // require service support
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
  });

const router = createRouter();

// Detail see: https://github.com/vuejs/vue-router/issues/1234#issuecomment-357941465
export function resetRouter() {
  const newRouter = createRouter();
  router.matcher = newRouter.matcher; // reset router
}

export default router;
