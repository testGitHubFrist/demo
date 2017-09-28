//common function
/**
* 创建矩形
* 参数canvas : DOM对象
* 参数ctx : 画布上绘图的环境
*/
function writeRect(canvas, ctx){
	delRect(ctx, 0, 0, canvas.width, canvas.height);
	
	ctx.beginPath();
	ctx.fillStyle = "rgba(255, 125, 0, 0.5)";
	ctx.fillRect(0, 0, canvas.width, canvas.height);
}

/**
* 清除矩形
* 参数ctx : 画布上绘图的环境
* 参数x,y : 矩形的左上角的坐标
* 参数width,height : 矩形的尺寸
*/
function delRect(ctx, x, y, width, height){
	ctx.clearRect(x, y, width, height);
}