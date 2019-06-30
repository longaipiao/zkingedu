package com.zking.zkingedu.common.controller;

import com.zking.zkingedu.common.model.Course;
import com.zking.zkingedu.common.model.Section;
import com.zking.zkingedu.common.model.Video;
import com.zking.zkingedu.common.service.CourseService;
import com.zking.zkingedu.common.service.SectionService;
import com.zking.zkingedu.common.service.UserService;
import com.zking.zkingedu.common.service.VideoService;
import com.zking.zkingedu.common.utils.PageBean;
import com.zking.zkingedu.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 章节视频  控制器
 * 颜
 */
@Controller
@RequestMapping("/user")
@Slf4j
public class SectionController {


    @Autowired
    private SectionService sectionService;

    @Autowired
    private VideoService videoService;



    @Autowired
    private CourseService courseService;

    /**
     * 用户点击章节开始学习  视频播放
     * @param sid 课程id
     * @param id    章节id
     * @return
     * 作者：颜
     */
    @RequestMapping("/video")
    public ModelAndView videoPlayer(@RequestParam(value = "sid") Integer sid, @RequestParam(value = "id") Integer id){
        ModelAndView mv = new ModelAndView();

        //获取所有的章节视频
        List<Section> sectionsBycid = sectionService.getSectionsBycid(sid);

        //根据章节id获取视频信息
//        Video videoById = videoService.getVideoById(id);
        Section section = sectionService.getSectionById(id);

        //获取课程信息
        Course courseBYcourseID = courseService.getCourseBYcourseID(sid);

        mv.addObject("course",courseBYcourseID);
        mv.addObject("section",section);//章节数据
        mv.addObject("sections",sectionsBycid);
        mv.setViewName("/user/courses/show");
        return mv;
    }


    /**
     * 后台用户在课程管理页面点击章节管理
     * 跳转至章节管理
     * 作者：颜
     * @param id
     * @return
     */
    @RequestMapping("/sectionManager")
    public ModelAndView pageSectionManager(@RequestParam(value = "id") Integer id,ModelAndView mv){
        mv.addObject("id",id);
        mv.setViewName("/admin/course/section/sectionManager");
        return mv;
    }


    /**
     * 阶段管理页面  数据加载  分页查询
     * @param page
     * @param limit
     * @param section
     * @return
     * yan
     */
    @ResponseBody
    @RequestMapping(value = "/getSectionsList")
    public ResultUtil getSectionDatas(Integer page,Integer limit,Section section,Integer id){
        PageBean<Section> pageBean = new PageBean<>();
        pageBean.setPageIndex(page);
        pageBean.setPageSize(limit);
        pageBean.setT(section);
        return  sectionService.getSectionByCidAndPageSearch(id,pageBean);
    }


    /**
     * 点击添加章节 跳转至章节添加页面
     * yan
     * @param cid
     * @param mv
     * @return
     */
    @RequestMapping(value = "/sectionAddPage")
    public ModelAndView pageSectionAdd(@RequestParam(value = "cid")Integer cid,ModelAndView mv){
        mv.addObject("id",cid);
        mv.setViewName("/admin/course/section/sectionAdd");
        return mv;
    }


    /**
     * 添加课程章节
     * yan
     * @param section
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping("/addSection")
    public ResultUtil addSection(Section section){
//        System.err.println("进来了==========================================");
//        System.err.println("section = " + section);
        if(section==null){
            return ResultUtil.error();
        }
        try {
            int i = sectionService.addSection(section);
            if(i>0){
                courseService.editFreeAndInte(section.getSectionCid());
                return ResultUtil.ok("添加成功");
            }
            else{
                return ResultUtil.error();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }


    /**
     * 点击修改  进入  加载章节数据
     * @param sid
     * @return
     */
    @RequestMapping("/perUpSection")
    public ModelAndView pagePreUpSection(@RequestParam(value = "sid") Integer sid,ModelAndView mv){
        Section section = sectionService.getSectionById(sid);
        mv.addObject("section",section);
        mv.setViewName("/admin/course/section/sectionEdit");
        return mv;
    }


    /**
     * 修改章节信息
     * yan
     * @param section
     * @return
     */
    @ResponseBody
    @RequestMapping("/updateSection")
    public ResultUtil editSection(Section section){
        try {
            Section section1 = sectionService.getSectionById(section.getSectionID());//查询章节信息，获取课程id'
            int i = sectionService.updateSection(section);
            if(i>0){
                courseService.editFreeAndInte(section1.getSectionCid());
                return ResultUtil.ok("修改成功");
            }
            else{
                return ResultUtil.error("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }


    /**
     * 批量删除章节
     * yan
     * @param ids
     * @return
     */
    @Transactional
    @ResponseBody
    @RequestMapping(value = "/delSections")
    public ResultUtil delSections(@RequestParam(value = "ids[]") List<Integer> ids){
        try {
            int i = sectionService.delSections(ids);
            if(i>0){
                //更新  课程免费章节数  以及课程购买总积分
                editCourseInteAndFree(ids);
                return ResultUtil.ok("操作成功");
            }
            return ResultUtil.error("您的操作过于频繁");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error(e.getMessage());
        }
    }




    /**
     * 更新  课程免费章节数  以及课程购买总积分
     * @param ids
     * @return
     * @throws Exception
     */
    public int editCourseInteAndFree(List<Integer> ids)throws Exception{
        Integer secId = ids.get(ids.size()-1);
        System.err.println("=========================章节id:"+secId);
        Section section = sectionService.getSectionById(secId);
        //开始更新课程数据
        int i = courseService.editFreeAndInte(section.getSectionCid());
        return i;
    }

}
