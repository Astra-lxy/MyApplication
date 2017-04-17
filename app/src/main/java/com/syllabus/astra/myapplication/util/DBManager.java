package com.syllabus.astra.myapplication.util;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/15.
 */

public class DBManager {
    private DBHelper helper;
    private SQLiteDatabase db;
    public DBManager(Context context) {
        helper = new DBHelper(context);
    }

    //-----------------------------
    //添加教师课程信息
    public void addTeacher(List<Teacher> teachers) {
        db = helper.getWritableDatabase();
        db.beginTransaction();//开始事务
        try{
            for(Teacher teacher : teachers) {
                db.execSQL("insert into teacher values(null,?, ?, ?, ?, ?, ?, ?, ?)",new Object[]{teacher.getId(),teacher.getMon(),teacher.getTues(),teacher.getWed(),teacher.getThur(),teacher.getFri(),teacher.getSat(), teacher.getSun()});
            }
            db.setTransactionSuccessful();
        } finally{
            db.endTransaction();
        }
    }

    //查询老师课程表--根据id
    public Cursor queryTheTeacher(String id) {
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM teacher where id = ?", new String[]{id});
        return c;
    }

    public List<Teacher>  queryTeacher(String id) {

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        Cursor c = queryTheTeacher(id);
        while(c.moveToNext()){
            Teacher teacher = new Teacher();
            teacher.setId(c.getString(c.getColumnIndex("id")));
            teacher.setMon(c.getString(c.getColumnIndex("mon")));
            teacher.setTues(c.getString(c.getColumnIndex("tues")));
            teacher.setWed(c.getString(c.getColumnIndex("wed")));
            teacher.setThur(c.getString(c.getColumnIndex("thur")));
            teacher.setFri(c.getString(c.getColumnIndex("fri")));
            teacher.setSat(c.getString(c.getColumnIndex("sat")));
            teacher.setSun(c.getString(c.getColumnIndex("sun")));
            teachers.add(teacher);
        }
        c.close();
        return teachers;
    }

//-----------------------------

    //添加老师列表
    public void addTeacherList(List<TeacherList> teachers) {
        db = helper.getWritableDatabase();
        db.beginTransaction();
        try {
            for (TeacherList teacher : teachers) {
                db.execSQL("INSERT INTO teacherlist VALUES(null, ?, ?)", new Object[]{teacher.getId(),teacher.getName()});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }
    }

    //查询老师列表
    public Cursor queryTheTeacherList() {
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM teacherlist", null);
        return c;

    }

    public List<TeacherList> queryTeacherList() {
        ArrayList<TeacherList> teachers = new ArrayList<TeacherList>();
        Cursor c = queryTheTeacherList();
        while (c.moveToNext()) {
            TeacherList teacherlist = new TeacherList();
            teacherlist.setId(c.getString(c.getColumnIndex("id")));
            teacherlist.setName(c.getString(c.getColumnIndex("name")));
            teachers.add(teacherlist);
        }
        c.close();
        return teachers;
    }
//-----------------------------

    //添加课目课程信息
    /*public void addCourse(List<Course> courses) {
        db = helper.getWritableDatabase();
        db.beginTransaction();//开始事务
        try{
            for(Course course : courses) {
                db.execSQL("insert into course values(null,?, ?, ?, ?, ?, ?, ?, ?)",new Object[]{course.getId(),course.getMon(),course.getTues(),course.getWed(),course.getThur(),course.getFri(),course.getSat(), course.getSun()});
            }
            db.setTransactionSuccessful();
        } finally{
            db.endTransaction();
        }
    }
    //查询课目课程信息
    public Cursor queryTheCourse(String id) {
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM course where id = ?", new String[]{id});
        return c;
    }
    public List<Course>  queryCourse(String id) {

        ArrayList<Course> courses = new ArrayList<Course>();
        Cursor c = queryTheCourse(id);
        while(c.moveToNext()){
            Course course = new Course();
            course.setId(c.getString(c.getColumnIndex("id")));
            course.setMon(c.getString(c.getColumnIndex("mon")));
            course.setTues(c.getString(c.getColumnIndex("tues")));
            course.setWed(c.getString(c.getColumnIndex("wed")));
            course.setThur(c.getString(c.getColumnIndex("thur")));
            course.setFri(c.getString(c.getColumnIndex("fri")));
            course.setSat(c.getString(c.getColumnIndex("sat")));
            course.setSun(c.getString(c.getColumnIndex("sun")));
            courses.add(course);
        }
        c.close();
        return courses;
    }


//-----------------------------
    //添加课目课程列表
    public void addCourseList(List<CourseList> courses) {
        db = helper.getWritableDatabase();
        db.beginTransaction();  //开始事务
        try {
            for (CourseList course : courses) {
                db.execSQL("INSERT INTO courselist VALUES(null, ?, ?)", new Object[]{course.getId(), course.getName()});
            }
            db.setTransactionSuccessful();  //设置事务成功完成
        } finally {
            db.endTransaction();    //结束事务
        }

    }

    //查询课目课程列表
    public Cursor queryTheCourseList() {
        db = helper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM courselist", null);
        return c;

    }
    public List<CourseList> queryCourseList() {
        ArrayList<CourseList> courses = new ArrayList<CourseList>();
        Cursor c  = queryTheCourseList();
        while(c.moveToNext()) {
            CourseList courselist = new CourseList();
            courselist.setId(c.getString(c.getColumnIndex("id")));
            courselist.setName(c.getString(c.getColumnIndex("name")));
            courses.add(courselist);
        }
        c.close();
        return courses;
    }

//-----------------------------
    //添加教室课程信息
//    public void addClassroomList(List<CourseList> courses) {
//        db = helper.getWritableDatabase();
//        db.beginTransaction();  //开始事务
//        try {
//            for (CourseList course : courses) {
//                db.execSQL("INSERT INTO courselist VALUES(null, ?, ?)", new Object[]{course.getId(), course.getName()});
//            }
//            db.setTransactionSuccessful();  //设置事务成功完成
//        } finally {
//            db.endTransaction();    //结束事务
//        }
//
//    }

        */





    public void closeDB() {
        db.close();
    }
}
