package com.zking.zkingedu.common.controller;

import com.google.gson.Gson;
import com.zking.zkingedu.common.model.Emp;
import com.zking.zkingedu.common.model.Menu;
import com.zking.zkingedu.common.service.EmpService;
import com.zking.zkingedu.common.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/admin")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 获取所有菜单权限
     * @return
     */
    @RequestMapping(value = "/getMenus")
    @ResponseBody
    public Object getMenus(){
        List<Map> list = new ArrayList<>();//已经处理好的包含所有菜单项的集合
        List<Menu> menus = menuService.getMenus();//获取所有菜单项
//        for (Menu menu : menus) {
//            System.out.println("menu = " + menu);
//        }
        if(menus!=null&&menus.size()!=0){//判断有数据
            for (int i = 0; i < menus.size(); ++i) {//遍历菜单集合
                if(((Menu)menus.get(i)).getMenuFid()==0){//如果菜单父ID==0 证明是最顶级的
                    Map<String,Object> map = new HashMap<>();//创建一个父集合
                    map.put("id",((Menu)menus.get(i)).getMenuID());//把菜单ID赋值给树ID
                    map.put("title",((Menu)menus.get(i)).getMenuName());//把菜单名字赋值给树NAME
                    List<Map> list1 = new ArrayList<>();//创建一个子集合
                    for (int j = 0; j < menus.size(); ++j) {//再次遍历菜单集合
                        if(((Menu)menus.get(i)).getMenuID()==((Menu)menus.get(j)).getMenuFid()) {//所有菜单的父ID等于顶级菜单的ID
                            Map map1 = new HashMap();//实例化一个map集合
                            map1.put("id",((Menu)menus.get(j)).getMenuID());//把菜单ID赋值给树ID
                            map1.put("title",((Menu)menus.get(j)).getMenuName());//把菜单名字赋值给树NAME
                            list1.add(map1);//给子集合赋值
                        }
                    }

                    map.put("children",list1);//把集合放入map集合
                    list.add(map);//把map放入list集合
                }
            }
        }

        //查询出所有的树菜单
//        @Override
//        public List<Treedata> getdata() {
//            List<Treedata> results = new ArrayList();//装备树数据源的集合
//            List<Menu> menus = menuDao.getmenus();//查询出所有的菜单集合
//            if(menus!=null&&menus.size()!=0){//判断有数据
//                for (int i = 0; i < menus.size(); ++i) {//遍历菜单集合
//                    if(((Menu)menus.get(i)).getMenuFid()==0){//如果菜单父ID==0 证明是最顶级的
//                        Treedata treedata = new Treedata();//实例化一个树对象
//                        treedata.setId(((Menu)menus.get(i)).getMenuID());//把菜单ID赋值给树ID
//                        treedata.setTitle(((Menu)menus.get(i)).getMenuName());//把菜单名字赋值给树NAME
//                        List<Treedata> treelist = new ArrayList();//创建一个树集合
//                        for (int j = 0; j < menus.size(); ++j) {//再次遍历菜单集合
//                            if(((Menu)menus.get(i)).getMenuID()==((Menu)menus.get(j)).getMenuFid()) {//所有菜单的父ID等于顶级菜单的ID
//                                Treedata treedata1 = new Treedata();//再实例化一个树对象
//                                treedata1.setId(((Menu)menus.get(j)).getMenuID());//把菜单ID赋值给树ID
//                                treedata1.setTitle(((Menu)menus.get(j)).getMenuName());//把菜单名字赋值给树NAME
//                                treelist.add(treedata1);//把第二个树对象赋值给树集合
//                            }
//                        }
//                        treedata.setChildren(treelist);//把树集合放入树对象
//                        results.add(treedata);//把一个个树对象放入树数据源的集合
//                    }
//                }
//            }
//            return results;
//        }


        return list;
    }
    /**
     * 获取该角色所持有的菜单权限
     * @return
     */
    @RequestMapping(value = "/getMenuByRoleID")
    @ResponseBody
    public List<Menu> getMenuByRoleID(HttpServletRequest request){
        String roleid = request.getParameter("roleid");
        List<Menu> menus = menuService.getMenuByRoleID(Integer.parseInt(roleid));

        return menus;
    }

}
