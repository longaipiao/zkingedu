package com.zking.zkingedu.common.service.impl;

import com.zking.zkingedu.common.dao.CategoryDao;
import com.zking.zkingedu.common.model.Category;
import com.zking.zkingedu.common.service.CategoryService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 题库类别接口服务层
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryDao categorydao;

    @Override
    public List<Category> getAll() {
        return categorydao.getAll();
    }

    @Override
    public List<Category> getAllfid(Integer fid) {
        return categorydao.getAllfid(fid);
    }

    @Override
    public Integer addCategory(Category category) {
        return categorydao.addCategory(category);
    }

    @Override
    public Integer updatety(Integer aid) {
        return categorydao.updatety(aid);
    }

    @Override
    public Integer updatekq(Integer aid) {
        return categorydao.updatekq(aid);
    }

    @Override
    public Integer delete(Integer aid, Integer fid) {
        return categorydao.delete(aid, fid);
    }

    @Override
    public Integer updateName(Integer aid, String name,Integer fid) {
        return categorydao.updateName(aid,name,fid);
    }

    @Override
    public Integer updateNames(Integer aid, Integer fid, String name) {
        return categorydao.updateNames(aid, fid, name);
    }

    @Override
    public Category getcat(Integer categoryID) {
        return categorydao.getcat(categoryID);
    }

    /**
     * 查询所有的题库类别
     * @return
     */
    @Override
    public List<Category> getCategory() {
        List<Category> results = new ArrayList<>();
        List<Category> categories = categorydao.getCategory();//获取所有的题库类别
        if(categories!=null&categories.size()!=0){//判空
            for(int i = 0; i < categories.size(); ++i){//遍历该集合
                if(((Category)categories.get(i)).getCategoryFid()==0){//顶级类别
                    Category category = new Category();//实例化一个集合
                    category.setCategoryID(((Category)categories.get(i)).getCategoryID());
                    category.setCategoryName(((Category)categories.get(i)).getCategoryName());
                    category.setCategoryFid(((Category)categories.get(i)).getCategoryFid());
                    category.setCategoryTime(((Category)categories.get(i)).getCategoryTime());
                    category.setCategoryEid(((Category)categories.get(i)).getCategoryEid());
                    category.setCategoryRank(((Category)categories.get(i)).getCategoryRank());
                    category.setCategoryState(((Category)categories.get(i)).getCategoryState());
                    List<Category> list = new ArrayList<>();
                    for(int j = 0; j < categories.size(); ++j) {//再次遍历该集合
                        if(((Category)categories.get(i)).getCategoryID()==((Category)categories.get(j)).getCategoryFid()){
                            Category categorya = new Category();//再次实例化一个集合
                            categorya.setCategoryID(((Category)categories.get(j)).getCategoryID());
                            categorya.setCategoryName(((Category)categories.get(j)).getCategoryName());
                            categorya.setCategoryFid(((Category)categories.get(j)).getCategoryFid());
                            categorya.setCategoryTime(((Category)categories.get(j)).getCategoryTime());
                            categorya.setCategoryEid(((Category)categories.get(j)).getCategoryEid());
                            categorya.setCategoryRank(((Category)categories.get(j)).getCategoryRank());
                            categorya.setCategoryState(((Category)categories.get(j)).getCategoryState());
                            list.add(categorya);
                        }
                    }
                    category.setCategories(list);
                    results.add(category);
                }
            }
        }
        return results;
    }
    /**
     * 查询所有的题库类别
     * @return
     */
    @Override
    public List<Category> getCategoryall() {
        List<Category> results = new ArrayList<>();
        List<Category> categories = categorydao.getCategoryall();//获取所有的题库类别
        if(categories!=null&categories.size()!=0){//判空
            for(int i = 0; i < categories.size(); ++i){//遍历该集合
                if(((Category)categories.get(i)).getCategoryFid()==0){//顶级类别
                    Category category = new Category();//实例化一个集合
                    category.setCategoryID(((Category)categories.get(i)).getCategoryID());
                    category.setCategoryName(((Category)categories.get(i)).getCategoryName());
                    category.setCategoryFid(((Category)categories.get(i)).getCategoryFid());
                    category.setCategoryTime(((Category)categories.get(i)).getCategoryTime());
                    category.setCategoryEid(((Category)categories.get(i)).getCategoryEid());
                    category.setCategoryRank(((Category)categories.get(i)).getCategoryRank());
                    category.setCategoryState(((Category)categories.get(i)).getCategoryState());
                    List<Category> list = new ArrayList<>();
                    for(int j = 0; j < categories.size(); ++j) {//再次遍历该集合
                        if(((Category)categories.get(i)).getCategoryID()==((Category)categories.get(j)).getCategoryFid()){
                            Category categorya = new Category();//再次实例化一个集合
                            categorya.setCategoryID(((Category)categories.get(j)).getCategoryID());
                            categorya.setCategoryName(((Category)categories.get(j)).getCategoryName());
                            categorya.setCategoryFid(((Category)categories.get(j)).getCategoryFid());
                            categorya.setCategoryTime(((Category)categories.get(j)).getCategoryTime());
                            categorya.setCategoryEid(((Category)categories.get(j)).getCategoryEid());
                            categorya.setCategoryRank(((Category)categories.get(j)).getCategoryRank());
                            categorya.setCategoryState(((Category)categories.get(j)).getCategoryState());
                            list.add(categorya);
                        }
                    }
                    category.setCategories(list);
                    results.add(category);
                }
            }
        }
        return results;
    }


    /**
     * 根据父题库ID查询子题库字段
     * @param categoryFID
     * @return
     */
    @Override
    public List<Category> gettikuzitype(@Param("categoryFID") Integer categoryFID){
        return categorydao.gettikuzitype(categoryFID);
    }
}
