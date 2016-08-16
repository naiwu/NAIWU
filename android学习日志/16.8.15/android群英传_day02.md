# 1 自定义view
![](15.png)

目前通常一下有三种方法实现自定义view

1. 对现有组件进行拓张(继承)
2. 通过组合来实现新的空间
3. 重写view来实现新的空间

**注意:自定义控件需要引入多种构造方法!**

###1.1 对现有组件进行拓张
![](16.png)

----------

示例如下:

![](18.png)

![](17.png)


----------
利用android 的paint对象的shader渲染器.通过设置一个不断变化的LinearGradient,并使用带有该
属性的Paint来绘制文字.
![](20.png)

**LinearGradient:** Create a shader that draws a linear gradient along a line.

	public class ShineView extends TextView {
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private Paint mPaint;
    private int mViewWidth = 0;
    private int mTranslate = 0;
    public ShineView(Context context) {
        super(context);
    }

    public ShineView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ShineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if (mViewWidth>0){
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,
                        new int[]{Color.BLUE,Color.WHITE,Color.BLUE},null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mMatrix = new Matrix();
            }
        }


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mMatrix!=null){
            mTranslate+= mViewWidth/10;
            if (mTranslate>2*mViewWidth){
                mTranslate = -mViewWidth;
            }
            mMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mMatrix);
            postInvalidateDelayed(100);
        }


    }
}


###1.2 创建复合控件

![](19.png)