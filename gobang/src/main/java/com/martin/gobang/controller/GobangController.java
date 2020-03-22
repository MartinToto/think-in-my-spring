package com.martin.gobang.controller;

import com.martin.gobang.model.Gobang;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Date;
import java.util.UUID;

/**
 * @author Martin
 * @description
 * @date 2020/3/15
 */
@Controller
@RequestMapping("/")
public class GobangController {

    @RequestMapping(value = "/init", method = RequestMethod.GET)
    @ResponseBody
    public String findOrders(HttpServletResponse response,
                             HttpServletRequest request) {
        Integer width = 19;
        Integer length = 19;
        Gobang gobang = new Gobang(width, length);
        UUID uuid = UUID.randomUUID();
        Integer hashCode = uuid.hashCode();
        String roomNumber = hashCode.toString();
        int[][] core = getCoreDate(width, length,roomNumber);
        gobang.setCore(core);
        System.out.println(gobang.toString());
        return roomNumber;
    }

    @Cacheable(value = "core", key = "core")
    public int[][] getCoreDate(Integer width, Integer length,String roomNumber) {

        int[][] core = new int[width][length];

        CacheManager cacheManager = CacheManager.create();
        cacheManager.addCache(roomNumber);
        Cache coreCache = cacheManager.getCache(roomNumber);
        Element element = new Element(roomNumber, core);
        coreCache.put(element);
        return core;
    }

    public int[][] getCacheCoreDate(Integer width, Integer length,String roomNumber) {

        CacheManager cacheManager = CacheManager.create();
        Cache core = cacheManager.getCache(roomNumber);
        Element care = core.get(roomNumber);
        int[][] coreArray = (int[][]) care.getObjectValue();
        return coreArray;
    }

    @RequestMapping(value = "/operation", method = RequestMethod.GET)
    @ResponseBody
    public String operation(@RequestParam(value = "x", required = false) Integer x,
                            @RequestParam(value = "y", required = false) Integer y,
                            @RequestParam(value = "var", required = false) Integer var,
                            @RequestParam(value = "roomNumber", required = false) String roomNumber,
                            HttpServletResponse response,
                            HttpServletRequest request) {

        int[][] core = getCacheCoreDate(19, 19,roomNumber);

        int i = chessIt(core, x, y, var);
        CacheManager cacheManager = CacheManager.create();
        Cache coreCache = cacheManager.getCache(roomNumber);
//        1:white 赢   2:black赢
        if (i == 1) {
            coreCache.remove(roomNumber);
            return "你赢了";
        }
        if (i == 2) {
            coreCache.remove(roomNumber);
            return "你输了";
        }
        System.out.println(core.toString());
        StringBuffer stringBuffer = new StringBuffer();
        for (int j = 0; j < core.length; j++) {
            for (int anInt : core[j]) {
                System.out.print(anInt);
                System.out.print("    ");
                stringBuffer.append(anInt).append("  ");
            }
            System.out.println(" ");
            stringBuffer.append("\n");
        }

        return stringBuffer.toString();
    }
    //初始化一个棋盘
    //将棋盘放入栈中
    //接受一个坐标
    //判断是否赢棋
    //返回结果


    //检查该地是否有空位置
    private boolean __CanInput(int[][] core, int x, int y) {
        if (core[x][y] == 0) return true;
        else return false;
    }

    /**
     * 在该位置下棋  1:white 2:black
     *
     * @param x   横坐标
     * @param y   纵坐标
     * @param var 棋子种类
     * @return 1:white 赢   2:black赢
     */
    public int chessIt(int[][] core, int x, int y, int var) {
        if (__CanInput(core, x, y)) {
            core[x][y] = var;
            return checkVictory(core, x, y, var);
        } else return -1;
    }

    //判断输赢
    private int checkVictory(int[][] core, int x, int y, int var) {
        //左斜上 到 右斜下
        Integer leftUp = getLeftUp2RightDown(core, x, y, var);
        if (leftUp != -1) {
            return leftUp;
        }
        //左斜下 到 右斜上
        Integer leftDown = getLeftDown2RightUp(core, x, y, var);
        if (leftDown != -1) {
            return leftDown;
        }

        //横 transverse
        Integer leftTransverse = getTransverse(core, x, y, var);
        if (leftTransverse != -1) {
            return leftTransverse;
        }

        //纵 longitudinal
        Integer leftLongitudinal = getLongitudinal(core, x, y, var);
        if (leftLongitudinal != -1) {
            return leftLongitudinal;
        }


        return -1;
    }


    private Integer getLongitudinal(int[][] core, int x, int y, int var) {
        //y不变
        int length = core.length-1;
        int startX = x - 4;
        if (startX < 0) {
            startX = 0;
        }
        int endX = x + 4;
        if (endX > length) {
            endX = length;
        }
        //是否是连续性
        boolean isFlag = false;
        if (core[startX][y] == var) {
            isFlag = true;
        }
        int number = 0;
        for (int i = startX; i <= endX; i++) {
            if (number == 5 && isFlag) {
                return var;
            }
            if (core[i][y] == var) {
                number++;
                isFlag = true;
            } else {
                number = 0;
                isFlag = false;
            }
        }


        return -1;
    }


    private Integer getTransverse(int[][] core, int x, int y, int var) {
        //x不变
        int length = core.length-1;
        int startY = y - 4;
        if (startY < 0) {
            startY = 0;
        }
        int endY = y + 4;
        if (endY > length) {
            endY = length;
        }
        //是否是连续性
        boolean isFlag = false;
        if (core[x][startY] == var) {
            isFlag = true;
        }
        int number = 0;
        for (int i = startY; i <= endY; i++) {
            if (number == 5 && isFlag) {
                return var;
            }
            if (core[x][i] == var) {
                number++;
                isFlag = true;
            } else {
                number = 0;
                isFlag = false;
            }
        }
        return -1;
    }

    /**
     * 左斜下 到 右斜上
     * @param core
     * @param x
     * @param y
     * @param var
     * @return
     */
    private Integer getLeftDown2RightUp(int[][] core, int x, int y, int var) {
        int length = core.length -1;
        // y = -x + K
        int K = y+x;
        int startX = length;
        int startY = 0;
        int endX = 0;
        int endY = length;
        if(K<length){
            startY = 0;
            startX = K-startY;

            endX = 0;
            endY = -endX + K;

        }
        if(K>length){
            startX = length;
            startY = -startX + K;

            endY = length;
            endX = K - endY;
        }
        System.out.println("左斜下 到 右斜上 startX: "+startX+",startY:"+startY+",endX:"+endX+",endY:"+endY);
        //上一个棋子的状态 用以做连续性
        boolean isFlag = false;
        if (core[startX][startY] == var) {
            isFlag = true;
        }
        int number = 0;
        for (int i = startX, j = startY; i >= endX && j <= endY; i--, j++) {
            if (number == 5 && isFlag) {
                return var;
            }
            if (core[i][j] == var) {
                number++;
                isFlag = true;
            } else {
                number = 0;
                isFlag = false;
            }
        }
        return -1;
    }

    /**
     * //左斜上 到 右斜下
     * @param core
     * @param x
     * @param y
     * @param var
     * @return
     */
    private Integer getLeftUp2RightDown(int[][] core, int x, int y, int var) {
        int length = core.length-1;
        //y = x+k
        int K = y-x;
        int startX = 0;
        int startY = 0;
        int endX = length;
        int endY = length;
        if(K>0){
            startX = 0;
            startY = startY + K;
            endY = length;
            endX = endY - K;
        }
        if(K<0){
            startY = 0;
            startX = startY - K;
            endX = length;
            endY = endX - K;
        }
        System.out.println("左斜上 到 右斜下 startX: "+startX+",startY:"+startY+",endX:"+endX+",endY:"+endY);
        //上一个棋子的状态 用以做连续性
        boolean isFlag = false;
        if (core[startX][startY] == var) {
            isFlag = true;
        }
        int number = 0;
        for (int i = startX, j = startY; i <= endX && j <= endY; i++, j++) {
            if (number == 5 && isFlag) {
                return var;
            }
            if (core[i][j] == var) {
                number++;
                isFlag = true;
            } else {
                number = 0;
                isFlag = false;
            }
        }
        return -1;
    }
}
