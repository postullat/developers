var canvas;			//����� ��� ���������� ����� canvas��� ��������� canvas
var bufferCanvas;			
var ctx;
var bufferctx;

var WEIGTH = 50;	//������ ��������
var HEIGHT = 50;	//������ ��������

var ln_i = 0; 		//������� ������ mapObjects �� ��������� � �������
var ln_j = 0; 		//������� ������ mapObjects �� ��������� � �������
var path;			//���� �� �������� �� ������
var x;				//���������� � ��������
var y;				//���������� � ��������
var degree;			//��� �������� ��������

var dx;
var dy;
 

// ����������� canvas
function init_canvas(){
	

canvas = document.getElementById("map_canvas"); //������ ���� canvas
canvas.width = 1000;
canvas.height = 750;
ctx = canvas.getContext("2d");				//������ ���� ��������
getOffsetRect(canvas);

bufferCanvas = document.createElement("canvas");
bufferCanvas.width = 1000;
bufferCanvas.height = 750;
bufferctx = canvas.getContext("2d");				//������ ���� ��������
//getOffsetRect(bufferCanvas);

//$("#unit_canvas").progressbar();
}


// ��������� �� canvas
function draw(path, x, y, degree){
	
var img1 = new Image();

img1.src = path;						//������ ���� �� ��������, ��� ������ �� ������
img1.width = WEIGTH;
img1.height = HEIGHT;

img1.onload = function() {				//������� �� ���������� ���� ����������� ��������


	rotate(degree, img1, x, y);
	};
}

	//������� ��� �������� ��������
	function rotate(degree, img1, x, y){
		
		degree = degree*1;
		
		x = x * 1;							//������������ ����� � �����
		y = y * 1;
		
		bufferctx.save();
		bufferctx .translate(x+WEIGTH/2,y+HEIGHT/2);		
	
		bufferctx .rotate(degree * Math.PI / 180);
		bufferctx .drawImage(img1, -(WEIGTH/2), -(HEIGHT/2));
		bufferctx .restore();
	
	}


	//����������� ��������� ����� mapObjects �� ����
	function parseObject(object, len){
	
		init_canvas();								//��������� ������� ��������� � canvas
		
		ln_i = object.mapObjects.length;		//��������� ������� �����, �� ������� ������
		ln_j = object.mapObjects[0].length;		//��������� ������� �����, �� ������� ������
		
	
		
		for(var i=0; i<ln_i; i++){
			for(var j=0; j<ln_j; j++){
				
				if(object.mapObjects[i][j]!=null){
				path = object.mapObjects[i][j].serverPath;
				x = object.mapObjects[i][j].i*object.frameWidth;
				y = object.mapObjects[i][j].j*object.frameHeight;
				degree = object.mapObjects[i][j].rotationAngle;	//��� �������
				
	            
				draw(path, x, y, degree);
				}
			}
		}
		
		ctx.drawImage(bufferCanvas,0,0);
		
	}

	//������� ��� ��������� �������� �� �������� ����� �� ���������� �� ���������
	function get_MapObject(){						
		
		$.ajaxSetup({
			 contentType: "application/x-www-form-urlencoded"
			 });
		 
			   $.ajax({	    
			    url: "get_map",						//������� �� ����� ��������� ����� � � ����� �������� �������
			    data: {"message":"give_me_object"},	//json ���� ����������� ��������
			    success: function(object) {			//���� �� ���������� �� ��������
			    		 
			    	parseObject(object);			//�������� �������� ��� � ������� ������� �� �� ���������
			    }
			    
			   });
	}


