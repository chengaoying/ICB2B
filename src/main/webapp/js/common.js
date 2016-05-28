
/**
 * 把时间戳格式化成日期
 */
function formatTime(time) { 
	return new Date(parseInt(time)).toLocaleString().replace(/年|月/g, "-").replace(/日/g, " "); 
}

//判断是否是正整数
function IsNum(s)
{
    if(s!=null && s!=""){
        var r,re;
        re = /\d*/i; //\d表示数字,*表示匹配多个数字
        r = s.match(re);
        return (r==s)?true:false;
    }
    return false;
}

//判断是否是浮点数
function isFloat(c)
{
    var r= /^[+-]?[1-9]?[0-9]*\.[0-9]*$/;
    return r.test(c);
}