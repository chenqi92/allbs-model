[toc]
## 添加依赖

```
<dependency>
  <groupId>cn.allbs</groupId>
  <artifactId>allbs-model</artifactId>
  <version>0.5.0</version>
</dependency>
```

## 工具类
### JBF293K报文解析
```java
JBF293KMapper jbf293KMapper = new JBF293KMapper();
System.out.println(jbf293KMapper.readValue(bytes, Map.class));
```
#### 解析示例
![image-20230223134717092](https://nas.allbs.cn:9006/cloudpic/2023/02/144504d4725cbbc6bdd78e2dfa3e9ed8.png)
![image-20230223142712393](https://nas.allbs.cn:9006/cloudpic/2023/02/f273b83c4bae709c7ba4c606dcc6725c.png)
![image-20230223154854847](https://nas.allbs.cn:9006/cloudpic/2023/02/9bc4dda82d26129c592d105a6edace38.png)

### GB26875报文解析
```java
GB26875Mapper gb26875Mapper = new GB26875Mapper();
Map<String, Object> map = gb26875Mapper.readValue(bytes, Map.class);
```
![image-20230302092553966](https://nas.allbs.cn:9006/cloudpic/2023/03/4d19de823782d9f6f66ea330aecf81a6.png)

### 大气aqi计算类 AqiUtil
#### 计算实时/日的AQI数据 countRealAqi
```
public java.util.Map<java.lang.String,java.lang.Object> countRealAqi(java.util.Map<java.lang.String,java.lang.Double> pollutantValueMap,boolean isDay)
计算实时/日的AQI数据 SO2（μg/m³） NO2（μg/m³） PM10（μg/m³） CO(mg/m³) O3（μg/m³） PM2.5（μg/m³）
参数:
pollutantValueMap - 以因子code为key,浓度为value的 map
isDay - 是否为日平均aqi
返回:
aqi 数据和首要污染物code,超标污染物code 多个的情况下以逗号分隔
```


### 百度坐标系与墨卡托坐标系转换 BaiduMercatorToLngLatUtil
#### 墨卡托坐标转经纬度坐标  convertMC2LL
```
public static EarthPoint2D convertMC2LL(java.lang.Double x,
                                        java.lang.Double y)
墨卡托坐标转经纬度坐标
参数:
x - 墨卡托坐标x
y - 墨卡托坐标y
返回:
经纬度坐标
```

### java class 工具 ClassUtil
#### 类域获取 获取当前类包括父类的所有域 getClassFields
```
public java.lang.reflect.Field[] getClassFields(java.lang.Class<?> clazz)
类域获取
获取当前类包括父类的所有域

参数:
clazz - 需要遍历的类
返回:
所有域
```

### 日期区间map构建 DateStaticsSectionUtil
#### 传入开始时间 结束时间 构建一个分钟Map minuteSection
```
public java.util.Map<java.lang.String,java.math.BigDecimal> minuteSection(java.time.LocalDateTime startTime,
                                                                          java.time.LocalDateTime endTime,
                                                                          int interval,
                                                                          java.lang.String pattern)
分钟构建Map
传入开始时间 结束时间 构建一个Map

参数:
startTime - 开始时间
endTime - 结束时间
interval - 间隔分钟数
pattern - 格式化
返回:
Map
```
#### 传入开始时间 结束时间 构建一个分钟Map minuteSection
```
public java.util.Map<java.lang.String,java.math.BigDecimal> minuteSection(java.time.LocalDateTime startTime,
                                                                          java.time.LocalDateTime endTime,
                                                                          int interval)
分钟构建Map
传入开始时间 结束时间 构建一个Map

参数:
startTime - 开始时间
endTime - 结束时间
interval - 间隔分钟数
返回:
Map
```
#### 传入开始时间 结束时间 构建一个分钟Map 默认间隔一分钟 minuteSection
```
public java.util.Map<java.lang.String,java.math.BigDecimal> minuteSection(java.time.LocalDateTime startTime,
                                                                          java.time.LocalDateTime endTime,
                                                                          java.lang.String pattern)
分钟构建Map
传入开始时间 结束时间 构建一个Map 默认间隔一分钟

参数:
startTime - 开始时间
endTime - 结束时间
pattern - 格式化
返回:
Map
```
#### 传入开始时间 结束时间 构建一个分钟Map 默认间隔一分钟 minuteSection
```
public java.util.Map<java.lang.String,java.math.BigDecimal> minuteSection(java.time.LocalDateTime startTime,
                                                                          java.time.LocalDateTime endTime)
分钟构建Map
传入开始时间 结束时间 构建一个Map 默认间隔一分钟

参数:
startTime - 开始时间
endTime - 结束时间
返回:
Map
```
#### 开始结束时间构造月Map daySection
```
public java.util.Map<java.lang.String,java.math.BigDecimal> daySection(java.time.LocalDate startTime,
                                                                       java.time.LocalDate endTime,
                                                                       java.lang.String pattern)
开始结束时间构造月Map
构建以月为跨度，该月份中所有日为key value为0的map

参数:
startTime - 开始时间
endTime - 结束时间
pattern - 格式化
返回:
map
```
#### 开始结束时间构造月Map daySection
```
public java.util.Map<java.lang.String,java.math.BigDecimal> daySection(java.time.LocalDate startTime,
                                                                       java.time.LocalDate endTime)
开始结束时间构造月Map
构建以月为跨度，该月份中所有日为key value为0的map

参数:
startTime - 开始时间
endTime - 结束时间
返回:
map
```
#### 根据某一天构建固定间隔小时数的24小时Map dayHour
```
public java.util.Map<java.lang.String,java.math.BigDecimal> dayHour(java.time.LocalDate date,
                                                                    int interval,
                                                                    java.lang.String pattern)
天 24小时Map
根据某一天构建固定间隔小时数的Map

参数:
date - 某天日期
interval - 间隔小时数
pattern - 格式化
返回:
Map
```
#### 根据某一天构建固定间隔小时数的24小时Map 默认一小时 dayHour
```
public java.util.Map<java.lang.String,java.math.BigDecimal> dayHour(java.time.LocalDate date,
                                                                    java.lang.String pattern)
天 24小时Map
根据某一天构建固定间隔小时数的Map 默认一小时

参数:
date - 某天日期
pattern - 格式化
返回:
Map
```
#### 根据某一天构建固定间隔小时数的24小时Map 默认一小时 dayHour
```
public java.util.Map<java.lang.String,java.math.BigDecimal> dayHour(java.time.LocalDate date,
                                                                    int interval)
天 24小时Map
根据某一天构建固定间隔小时数的Map 默认一小时

参数:
date - 某天日期
interval - 间隔小时数
返回:
Map
```
#### 根据某一天构建固定间隔小时数的Map 默认一小时 dayHour
```
public java.util.Map<java.lang.String,java.math.BigDecimal> dayHour(java.time.LocalDate date)
天 24小时Map
根据某一天构建固定间隔小时数的Map 默认一小时

参数:
date - 某天日期
返回:
Map
```
#### 传入年、月构建该年月内所有天的Map monthDay
```
public java.util.Map<java.lang.String,java.math.BigDecimal> monthDay(int year,
                                                                     int month,
                                                                     java.lang.String pattern)
根据年月构建Map
传入年、月构建该年月内所有天的Map

参数:
year - 年
month - 月
pattern - 格式化
返回:
Map
```
#### 传入年、月构建该年月内所有天的Map monthDay
```
public java.util.Map<java.lang.String,java.math.BigDecimal> monthDay(int year,
                                                                     int month)
根据年月构建Map
传入年、月构建该年月内所有天的Map

参数:
year - 年
month - 月
返回:
Map
```
#### 传入年份,构建该年所有月份的Map yearMonth
```
public java.util.Map<java.lang.String,java.math.BigDecimal> yearMonth(int year,
                                                                      java.lang.String pattern)
根据年份构造Map
传入年份,构建该年所有月份的Map

参数:
year - 年
pattern - 格式化
返回:
Map
#### yearMonth
public java.util.Map<java.lang.String,java.math.BigDecimal> yearMonth(int year)
根据年份构造Map
传入年份,构建该年所有月份的Map

参数:
year - 年
返回:
Map
```

### 事故模拟 蒸发计算工具类 EvaporationUtil
#### 闪蒸蒸发速率 flash
```
public java.lang.Double flash(double qm,
                              double cp,
                              double tt,
                              double tb,
                              double hv)
闪蒸蒸发速率
参数:
qm - 物质泄漏速率 kg/s
cp - 泄漏液体的定压热容 kJ/(kg.K)
tt - 储存温度，单位为K
tb - 泄漏液体的沸点，单位为K
hv - 泄漏液体的蒸发热，单位为J/kg
返回:
热液体闪蒸蒸发速率，单位为kg/s
```
#### 热量蒸发速率 heatOfEvaporation
```
public java.lang.Double heatOfEvaporation(double a1,
                                          double t0,
                                          double tb,
                                          double h,
                                          double t)
热量蒸发速率
环境温度高于液体沸点时发生，如35摄氏度的环境温度下，沸点为20摄氏度的液体泄漏

参数:
a1 - 液池面积 m2
t0 - 环境温度 K
tb - 液体沸点 K
h - 液体蒸发热 J/kg
t - 蒸发时间 s
返回:
热量蒸发速率 kg/s
```
#### 质量蒸发速率 qualityOfEvaporation
```
public java.lang.Double qualityOfEvaporation(double t0,
                                             double u,
                                             double r,
                                             double m)
质量蒸发速率
参数:
t0 - 环境温度 K
u - 风速 m/s
r - 液池半径 m
m - 泄漏物质分子量
返回:
质量蒸发速率 kg/s
```
#### 液池蒸发总量 totalEvaporation
```
public java.lang.Double totalEvaporation(java.lang.Double qm,
                                         java.lang.Double cp,
                                         java.lang.Double tt,
                                         java.lang.Double tb,
                                         java.lang.Double hv,
                                         java.lang.Double a1,
                                         java.lang.Double t0,
                                         java.lang.Double h,
                                         java.lang.Double t,
                                         java.lang.Double u,
                                         java.lang.Double r,
                                         java.lang.Double m,
                                         java.lang.Double t1,
                                         java.lang.Double t2,
                                         java.lang.Double t3)
液池蒸发总量
参数:
qm - 物质泄漏速率 kg/s
cp - 泄漏液体的定压热容 kJ/(kg.K)
tt - 储存温度，单位为K
tb - 泄漏液体的沸点，单位为K
hv - 泄漏液体的蒸发热，单位为J/kg
a1 - 液池面积 m2
t0 - 环境温度 K
h - 液体蒸发热 J/kg
t - 蒸发时间 s
u - 风速 m/s
r - 液池半径 m
m - 泄漏物质分子量
t1 - 闪蒸蒸发时间 单位为s 为NUll时不计入总气云质量
t2 - 热量蒸发时间，单位为s 为NUll时不计入总气云质量
t3 - 从液体泄漏 到液体全部处理完毕的时间，单位为s 为NUll时不计入总气云质量
返回:
形成的气云质量 kg
```
#### 仅出现过热液体闪蒸蒸发情况 onlyFlash
```
public java.lang.Double onlyFlash(double qm,
                                  double cp,
                                  double tt,
                                  double tb,
                                  double hv,
                                  double t1)
仅出现过热液体闪蒸蒸发情况
参数:
qm - 物质泄漏速率 kg/s
cp - 泄漏液体的定压热容 kJ/(kg.K)
tt - 储存温度，单位为K
tb - 泄漏液体的沸点，单位为K
hv - 泄漏液体的蒸发热，单位为J/kg
t1 - 闪蒸蒸发时间 单位为s
返回:
形成的气云质量 kg
```
#### 仅出现热量增发的情况 onlyHeatOfEvaporation
```
public java.lang.Double onlyHeatOfEvaporation(double a1,
                                              double t0,
                                              double h,
                                              double t,
                                              double t2,
                                              double tb)
仅出现热量增发的情况
参数:
a1 - 液池面积 m2
t0 - 环境温度 K
h - 液体蒸发热 J/kg
t - 蒸发时间 s
t2 - 热量蒸发时间，单位为s
tb - 泄漏液体的沸点，单位为K
返回:
形成的气云质量 kg
```
#### 仅出现质量蒸发的情况 onlyQualityOfEvaporation
```
public java.lang.Double onlyQualityOfEvaporation(double t0,
                                                 double u,
                                                 double r,
                                                 double m,
                                                 double t3)
仅出现质量蒸发的情况
参数:
t0 - 环境温度 K
u - 风速 m/s
r - 液池半径 m
m - 泄漏物质分子量
t3 - 从液体泄漏 到液体全部处理完毕的时间，单位为s
返回:
形成的气云质量 kg
```

### 高斯模型（高斯烟羽 + 高斯烟团） GaussUtil

#### 地面点源扩散 计算方式 groundPointSource

```
public double groundPointSource(double q,
                                double u,
                                double x,
                                double y,
                                double z,
                                java.lang.Integer l)
地面点源扩散 计算方式
参数:
q - 物料连续泄漏的质量流量，单位kg/s
u - 平均风速m/s
x - x距离
y - y距离
z - z距离
l - 太阳辐射等级
返回:
空间上某点的污染物浓度 mg/m3
```
#### 高架点源扩散模式计算 highPowerContinuousDiffusion

```
public double highPowerContinuousDiffusion(double q,
                                           double u,
                                           double h,
                                           double x,
                                           double y,
                                           double z,
                                           java.lang.Integer l)
高架点源扩散模式计算
参数:
q - 物料连续泄漏的质量流量，单位kg/s
u - 平均风速m/s
h - 泄露源源高
x - x距离
y - y距离
z - z距离
l - 太阳辐射等级
返回:
空间上某点的污染物浓度 mg/m3
```
#### 高斯烟羽不带入扩散系数 powerContinuousDiffusionWithoutSigma

```
public double powerContinuousDiffusionWithoutSigma(double q,
                                                   double u,
                                                   double h,
                                                   double x,
                                                   double y,
                                                   double z)
高斯烟羽不带入扩散系数
参数:
q - 物料连续泄漏的质量流量，单位kg/s
u - 平均风速m/s
h - 泄露源源高 m
x - x距离 m
y - y距离 m
z - z距离 m
返回:
空间上某点的污染物浓度 mg/m3
```
#### 地面全部反射时的地面浓度 allGroundReflection

```
public double allGroundReflection(double q,
                                  double u,
                                  double h,
                                  double x,
                                  double y,
                                  java.lang.Integer l)
地面全部反射时的地面浓度
参数:
q - 物料连续泄漏的质量流量，单位kg/s
u - 平均风速m/s
h - 泄露源源高
x - x距离
y - y距离
l - 太阳辐射等级
返回:
空间上某点的污染物浓度 mg/m3
```
#### 高斯烟团模型(短时间内形成毒气云团，扩散时间远大于泄漏时间的扩散) 不包含系数计算 smokeConcentration

```
public double smokeConcentration(double q,
                                 double u,
                                 double t,
                                 double x,
                                 double y,
                                 double z)
高斯烟团模型(短时间内形成毒气云团，扩散时间远大于泄漏时间的扩散) 不包含系数计算
参数:
q - 瞬时排放的物料质量 kg
u - 平均风速 m/s
t - 扩散时间 s
x - 空间点 x距离
y - 空间点y距离
z - 空间点 z距离
返回:
气体浓度 mg/m3
```
#### 烟云抬升高度 liftingHeightOfSmokeCloud

```
public double liftingHeightOfSmokeCloud(double vs,
                                        double d,
                                        double ws)
烟云抬升高度
参数:
vs - 气云释放速度 单位m/s
d - 泄露出口直径 单位m
ws - 环境风速 单位m/s
返回:
烟云抬升高度 m
```
#### 烟羽扩散 calculate

```
public java.lang.Double calculate(java.lang.Double q,
                                  java.lang.Double x,
                                  java.lang.Double y,
                                  java.lang.Double z,
                                  java.lang.Double h,
                                  java.lang.Double u,
                                  int t)
烟羽扩散
参数:
q - 气载污染物源强，即释放率（mg/s）
x - 下风向距离（m）
y - 横截风向距离（m）
z - 距水平的高度（m）
h - 排口高度
u - 平均风速m/s
t - 时间s
返回:
等级
```

### 各坐标系之间经纬度互相换算 GPSConverterUtils

#### 百度坐标系(BD-09)转WGS坐标 bd09toWgs84

```
public static double[] bd09toWgs84(double lng,
                                   double lat)
百度坐标系(BD-09)转WGS坐标
参数:
lng - 百度坐标纬度
lat - 百度坐标经度
返回:
WGS84坐标数组
```
#### WGS坐标转百度坐标系(BD-09) wgs84toBd09

```
public static double[] wgs84toBd09(double lng,
                                   double lat)
WGS坐标转百度坐标系(BD-09)
参数:
lng - WGS84坐标系的经度
lat - WGS84坐标系的纬度
返回:
百度坐标数组
```
#### 火星坐标系(GCJ-02)转百度坐标系(BD-09) gcj02toBd09

```
public static double[] gcj02toBd09(double lng,
                                   double lat)
火星坐标系(GCJ-02)转百度坐标系(BD-09)
谷歌、高德——<百度

参数:
lng - 火星坐标经度
lat - 火星坐标纬度
返回:
百度坐标数组
```
#### 百度坐标系(BD-09)转火星坐标系(GCJ-02) bd09toGcj02

```
public static double[] bd09toGcj02(double bdLon,
                                   double bdLat)
百度坐标系(BD-09)转火星坐标系(GCJ-02)
百度——<谷歌、高德

参数:
bdLon - 百度坐标纬度
bdLat - 百度坐标经度
返回:
火星坐标数组
```
#### WGS84转GCJ02(火星坐标系) wgs84toGcj02

```
public static double[] wgs84toGcj02(double lng,
                                    double lat)
WGS84转GCJ02(火星坐标系)
参数:
lng - WGS84坐标系的经度
lat - WGS84坐标系的纬度
返回:
火星坐标数组
```
#### GCJ02(火星坐标系)转GPS84 gcj02toWgs84

```
public static double[] gcj02toWgs84(double lng,
                                    double lat)
GCJ02(火星坐标系)转GPS84
参数:
lng - 火星坐标系的经度
lat - 火星坐标系纬度
返回:
WGS84坐标数组
```
#### 纬度转换 transFormLat

```
public static double transFormLat(double lng,
                                  double lat)
纬度转换
参数:
lng - 经度
lat - 纬度
返回:
纬度转换值
```
#### 经度转换 transFormLng

```
public static double transFormLng(double lng,
                                  double lat)
经度转换
参数:
lng - 经度
lat - 纬度
返回:
经度转换值
```
#### 判断是否在国内，不在国内不做偏移 outOfChina

```
public static boolean outOfChina(double lng,
                                 double lat)
判断是否在国内，不在国内不做偏移
参数:
lng - 经度
lat - 纬度
返回:
true 国外 false 国内
```

### 水平方向喷射火模型 HorizontalJetFireUtil

#### 计算热辐射通量 count

```
public java.lang.Double count(double hc,
                              double x,
                              double m)
计算热辐射通量
参数:
hc - 燃烧热 kJ/kg
x - 距离 m
m - 质量流速 kg/s
返回:
距离x处接收的热辐射通量 Kw/m2
```

### 图片转为ASCII码 ImageToAsciiUtil

#### 将图片转为ASCII码并打印 printImage

```
public void printImage(java.awt.image.BufferedImage image)
将图片转为ASCII码并打印
参数:
image - BufferedImage
```
#### 缩略图片 makeSmallImage

```
public java.awt.image.BufferedImage makeSmallImage(java.lang.String srcImageName)
缩略图片 BufferedImage bufferedImage = ImageToAsciiUtil.makeSmallImage("图片路径", 期望最大高度, 为防止图片扭曲宽度放大比例); ImageToAsciiUtil.printImage(bufferedImage);
参数:
srcImageName - 图片路径
返回:
BufferedImage
```
#### 缩略图片 makeSmallImage

```
public java.awt.image.BufferedImage makeSmallImage(java.lang.String srcImageName,
                                                   int dstMaxSize,
                                                   int widthBlowRate)
缩略图片 BufferedImage bufferedImage = ImageToAsciiUtil.makeSmallImage("图片路径", 期望最大高度默认25, 为防止图片扭曲宽度放大比例默认1); ImageToAsciiUtil.printImage(bufferedImage);
参数:
srcImageName - 图片路径
dstMaxSize - 预计最大高度
widthBlowRate - 宽度放大比例
返回:
BufferedImage
```

### 数学区间计算工具 IntervalUtil

#### 判断dataValue是否在interval区间范围内 isInTheInterval

```
public static boolean isInTheInterval(java.lang.String dataValue,
                                      java.lang.String interval)
判断dataValue是否在interval区间范围内
参数:
dataValue - 数值类型的
interval - 正常的数学区间，包括无穷大等，如：(1,3)、more than 5%、(-∞,6]、(125%,135%)U(70%,80%)
返回:
true：表示dataValue在区间interval范围内，false：表示dataValue不在区间interval范围内
```
#### 判断是否在全闭区间内 checkInAllCloseInterval

```
public static boolean checkInAllCloseInterval(java.lang.Double min,
                                              java.lang.Double max,
                                              java.lang.Double checkNum)
全闭区间
判断是否在全闭区间内

参数:
min - 最小值
max - 最大值
checkNum - 校验值
返回:
true 区间内 false 不在区间内
```
#### 判断是否在全开区间内 checkInAllOpenInterval

```
public static boolean checkInAllOpenInterval(java.lang.Double min,
                                             java.lang.Double max,
                                             java.lang.Double checkNum)
全开区间
判断是否在全开区间内

参数:
min - 最小值
max - 最大值
checkNum - 校验值
返回:
true 区间内 false 不在区间内
```
#### 判断是否在左闭右开区间内 checkInLeftCloseInterval

```
public static boolean checkInLeftCloseInterval(java.lang.Double min,
                                               java.lang.Double max,
                                               java.lang.Double checkNum)
左闭右开区间
判断是否在左闭右开区间内

参数:
min - 最小值
max - 最大值
checkNum - 校验值
返回:
true 区间内 false 不在区间内
```
#### 判断是否在左开右闭区间区间内 checkInRightCloseInterval

```
public static boolean checkInRightCloseInterval(java.lang.Double min,
                                                java.lang.Double max,
                                                java.lang.Double checkNum)
左开右闭区间
判断是否在左开右闭区间区间内

参数:
min - 最小值
max - 最大值
checkNum - 校验值
返回:
true 区间内 false 不在区间内
```

### 液体泄漏模型 LiquidLeakageUtil

####  质量流率 count

```
public java.lang.Double count(double p,
                              double a,
                              double hl)
质量流率
参数:
p - 液体密度 kg/m3
a - 泄漏孔面积 m2
hl - 泄漏孔上方液体高度 m
返回:
与泄漏时间相乘后即为泄漏的液体质量 kg/s
```

### 经纬度操作 LngLatUtil

#### 计算两个经纬度位置距离(带扁率校准) getDistanceOfMeter

```
public double getDistanceOfMeter(double startLng,
                                 double startLat,
                                 double endLng,
                                 double endLat)
计算两个经纬度位置距离(带扁率校准)
参数:
startLng - 起始经度
startLat - 起始纬度
endLng - 结束经度
endLat - 结束纬度
返回:
距离 米
```
#### 计算经纬度距离（不带扁率校准, 默认为WGS84坐标） getDistance

```
public double getDistance(double startLng,
                          double startLat,
                          double endLng,
                          double endLat)
计算经纬度距离（不带扁率校准, 默认为WGS84坐标）
参数:
startLng - 起始经度
startLat - 起始纬度
endLng - 结束经度
endLat - 结束纬度
返回:
相差距离
```
#### 计算经纬度 带入坐标系进行判断后计算 getDistance

```
public double getDistance(double startLng,
                          double startLat,
                          double endLng,
                          double endLat,
                          CoordinateSystemEnum coordinateSystemEnum)
计算经纬度 带入坐标系进行判断后计算
参数:
startLng - 起始经度
startLat - 起始纬度
endLng - 结束经度
endLat - 结束纬度
coordinateSystemEnum - 坐标系枚举
返回:
相差距离
```
#### 根据一点的坐标与距离，以及方向，计算另外一点的位置（不带入扁率）正北0度即为纬度轴,横向为经度轴 calLocationByDistanceAndLocationAndDirection

```
public java.util.Map<java.lang.String,java.lang.Double> calLocationByDistanceAndLocationAndDirection(double angle,
                                                                                                     double startLng,
                                                                                                     double startLat,
                                                                                                     double distance)
根据一点的坐标与距离，以及方向，计算另外一点的位置（不带入扁率）正北0度即为纬度轴,横向为经度轴
参数:
angle - 角度，从正北顺时针方向开始计算
startLng - 起始点经度
startLat - 起始点纬度
distance - 距离，单位m
返回:
经纬度map
```
#### 带入坐标系计算距离角度外的一点 calLocationByDistanceAndLocationAndDirection

```
public java.util.Map<java.lang.String,java.lang.Double> calLocationByDistanceAndLocationAndDirection(double angle,
                                                                                                     double startLng,
                                                                                                     double startLat,
                                                                                                     double distance,
                                                                                                     CoordinateSystemEnum coordinateSystemEnum)
带入坐标系计算距离角度外的一点
根据一点的坐标与距离，以及方向，计算另外一点的位置（不带入扁率）正北0度即为纬度轴,横向为经度轴

参数:
angle - 角度，从正北顺时针方向开始计算
startLng - 起始点经度
startLat - 起始点纬度
distance - 距离，单位m
coordinateSystemEnum - 坐标系
返回:
经纬度map
```
#### 判断一个点是否在圆形区域内 isInCircle

```
public static boolean isInCircle(double lng1,
                                 double lat1,
                                 double lng2,
                                 double lat2,
                                 double radius)
判断一个点是否在圆形区域内
参数:
lat1 - 圆心纬度
lng1 - 圆心经度
lat2 - 坐标纬度
lng2 - 坐标经度
radius - 需要计算的半径d
返回:
true 在范围内 false 不在范围内
```
#### 判断一个点是否在圆形区域内 带入坐标系 isInCircle

```
public static boolean isInCircle(double lng1,
                                 double lat1,
                                 double lng2,
                                 double lat2,
                                 double radius,
                                 CoordinateSystemEnum coordinateSystemEnum)
判断一个点是否在圆形区域内 带入坐标系
参数:
lat1 - 圆心纬度
lng1 - 圆心经度
lat2 - 坐标纬度
lng2 - 坐标经度
radius - 需要计算的半径
coordinateSystemEnum - 坐标系
返回:
true 在范围内 false 不在范围内
```
#### 判断是否在多边形区域内 isInPolygon

```
public static boolean isInPolygon(double pointLon,
                                  double pointLat,
                                  cn.hutool.json.JSONArray points)
判断是否在多边形区域内
参数:
pointLon - 要判断的点的纵坐标
pointLat - 要判断的点的横坐标
points - 经纬度json数组 "[{\"x\":120.61123416,\"y\":31.32889074,\"z\":137.05},{\"x\":120.61312695,\"y\":31.31892631,\"z\":128.61},{\"x\":120.61455616,\"y\":31.30808702,\"z\":43.66},{\"x\":120.62127327,\"y\":31.30899876,\"z\":62.21},{\"x\":120.63003506,\"y\":31.31057071,\"z\":29.43},{\"x\":120.63726235,\"y\":31.31203339,\"z\":92.90},{\"x\":120.64536616,\"y\":31.31334188,\"z\":78.36},{\"x\":120.64402082,\"y\":31.31947999,\"z\":13.19},{\"x\":120.64136126,\"y\":31.32757908,\"z\":87.36},{\"x\":120.63689776,\"y\":31.33287239,\"z\":60.62},{\"x\":120.63502091,\"y\":31.33742080,\"z\":114.21},{\"x\":120.63071787,\"y\":31.33793104,\"z\":32.99},{\"x\":120.62952446,\"y\":31.34483170,\"z\":164.79},{\"x\":120.62710968,\"y\":31.34801804,\"z\":164.15},{\"x\":120.62731359,\"y\":31.34823458,\"z\":189.53},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62665860,\"y\":31.34861797,\"z\":155.41},{\"x\":120.61706620,\"y\":31.34846463,\"z\":200.05},{\"x\":120.61854348,\"y\":31.34267516,\"z\":138.68},{\"x\":120.62111689,\"y\":31.33313042,\"z\":154.61}]"
返回:
true 在范围内 false 不在范围内
```
#### 判断是否在多边形区域内 isInPolygon

```
public static boolean isInPolygon(double pointLon,
                                  double pointLat,
                                  cn.hutool.json.JSONArray points,
                                  CoordinateSystemEnum coordinateSystemEnum)
判断是否在多边形区域内
参数:
pointLon - 要判断的点的纵坐标
pointLat - 要判断的点的横坐标
points - 经纬度json数组 "[{\"x\":120.61123416,\"y\":31.32889074,\"z\":137.05},{\"x\":120.61312695,\"y\":31.31892631,\"z\":128.61},{\"x\":120.61455616,\"y\":31.30808702,\"z\":43.66},{\"x\":120.62127327,\"y\":31.30899876,\"z\":62.21},{\"x\":120.63003506,\"y\":31.31057071,\"z\":29.43},{\"x\":120.63726235,\"y\":31.31203339,\"z\":92.90},{\"x\":120.64536616,\"y\":31.31334188,\"z\":78.36},{\"x\":120.64402082,\"y\":31.31947999,\"z\":13.19},{\"x\":120.64136126,\"y\":31.32757908,\"z\":87.36},{\"x\":120.63689776,\"y\":31.33287239,\"z\":60.62},{\"x\":120.63502091,\"y\":31.33742080,\"z\":114.21},{\"x\":120.63071787,\"y\":31.33793104,\"z\":32.99},{\"x\":120.62952446,\"y\":31.34483170,\"z\":164.79},{\"x\":120.62710968,\"y\":31.34801804,\"z\":164.15},{\"x\":120.62731359,\"y\":31.34823458,\"z\":189.53},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62700980,\"y\":31.34894193,\"z\":194.24},{\"x\":120.62665860,\"y\":31.34861797,\"z\":155.41},{\"x\":120.61706620,\"y\":31.34846463,\"z\":200.05},{\"x\":120.61854348,\"y\":31.34267516,\"z\":138.68},{\"x\":120.62111689,\"y\":31.33313042,\"z\":154.61}]"
coordinateSystemEnum - 坐标系
返回:
true 在范围内 false 不在范围内
```
#### 计算是否在扇形区域内 isInSector

```
public static boolean isInSector(double startLng,
                                 double startLat,
                                 double angel,
                                 double diffuse,
                                 double checkLng,
                                 double checkLat)
计算是否在扇形区域内
参数:
startLng - 起始经度
startLat - 其实纬度
angel - 需要计算的角度
diffuse - 计算角度向两边扩散度数
checkLng - 需要校验的经度
checkLat - 需要校验的纬度
返回:
true 在扇形范围内 false 不在扇形范围内
```
#### 计算是否在扇形区域内 isInSector

```
public static boolean isInSector(double startLng,
                                 double startLat,
                                 double angel,
                                 double diffuse,
                                 double checkLng,
                                 double checkLat,
                                 java.lang.Double distance)
计算是否在扇形区域内
参数:
startLng - 起始经度
startLat - 其实纬度
angel - 需要计算的角度
diffuse - 计算角度向两边扩散度数
checkLng - 需要校验的经度
checkLat - 需要校验的纬度
distance - 计算距离
返回:
true 在扇形范围内 false 不在扇形范围内
```
#### 根据一点的坐标与距离，以及方向，计算另外一点的位置（不带入扁率）正北0度即为纬度轴,横向为经度轴 calLocationByDistanceAndLocationAndDirection

```
public Point3D calLocationByDistanceAndLocationAndDirection(double angle,
                                                            Point3D point3D,
                                                            double distance)
根据一点的坐标与距离，以及方向，计算另外一点的位置（不带入扁率）正北0度即为纬度轴,横向为经度轴
参数:
angle，从正北顺时针方向开始计算 -
point3D - 计算位置的
distance - 距离，单位m
返回:
经纬度map
```
#### 求一点相对于另一点旋转一定角度后的坐标 route

```
public static java.lang.Double[] route(java.lang.Double[] cenerPoint,
                                       java.lang.Double[] point,
                                       java.lang.Double legel)
求一点相对于另一点旋转一定角度后的坐标
参数:
cenerPoint - 中心点
point - 待旋转的点
legel - 旋转角度
返回:
坐标数组
```
#### 偏移 依照墨卡托坐标计算 deviation

```
public static java.lang.Double[] deviation(java.lang.Double[] pointMercator)
偏移 依照墨卡托坐标计算
参数:
pointMercator - 点位
返回:
墨卡托坐标数组
```

### 模型综合计算的工具类 ModelUtil

#### 池火灾事故计算模型 poolFire

```

public java.util.Set<SpacePoint> poolFire(double g,
                                          double p0,
                                          double n,
                                          double hc,
                                          double m,
                                          double d,
                                          double lng,
                                          double lat,
                                          double height,
                                          double distance,
                                          double extendDistance)
池火灾事故计算模型
参数:
g - 重力加速度，取9.8m/s2
p0 - 空气密度，kg/m3
n - 效率因子 取值为0.13-0.35
hc - 液体燃烧热，kJ/kg
m - 单位池面积质量燃烧率 kg/(m2·s)
d - 液池直径，m
lng - 起火中心点经度
lat - 起火中心点纬度
height - 起火中心点高度
distance - 扩散距离
extendDistance - 子扩散距离用于渲染
返回:
空间点(经纬度, 高度) 和所在位置浓度
```

#### 水平方向喷射火模型计算 horizontalFire

```

public java.util.Set<SpacePoint> horizontalFire(double hc,
                                                double m,
                                                double lng,
                                                double lat,
                                                double height,
                                                double distance,
                                                double extendDistance)
水平方向喷射火模型计算
参数:
hc - 燃烧热 kJ/kg
m - 质量流速 kg/s
lng - 起火中心点经度
lat - 起火中心点纬度
height - 起火中心点高度
distance - 扩散距离
extendDistance - 子扩散距离用于渲染
返回:
空间点中热辐射通量 Kw/m2
```

#### 容器爆炸模型计算 vesselExplosion

```

public java.util.Set<SpacePoint> vesselExplosion(double p,
                                                 double v,
                                                 double lng,
                                                 double lat,
                                                 double height,
                                                 double distance,
                                                 double extendDistance)
容器爆炸模型计算
参数:
p - 容器内气体的绝对压力（MPa）
v - V为容器的容积（m3）
lng - 起火中心点经度
lat - 起火中心点纬度
height - 起火中心点高度
distance - 扩散距离
extendDistance - 子扩散距离用于渲染
返回:
空间点中冲击波的超压值
```

#### 蒸汽云爆炸模型 vaporCloudExplosion

```

public java.util.Set<SpacePoint> vaporCloudExplosion(java.lang.Double qm,
                                                     java.lang.Double cp,
                                                     java.lang.Double tt,
                                                     java.lang.Double tb,
                                                     java.lang.Double hv,
                                                     java.lang.Double a1,
                                                     java.lang.Double t0,
                                                     java.lang.Double h,
                                                     java.lang.Double t,
                                                     java.lang.Double u,
                                                     java.lang.Double r,
                                                     java.lang.Double m,
                                                     java.lang.Double t1,
                                                     java.lang.Double t2,
                                                     java.lang.Double t3,
                                                     double q,
                                                     double lng,
                                                     double lat,
                                                     double height,
                                                     double distance,
                                                     double extendDistance)
蒸汽云爆炸模型
参数:
qm - 物质泄漏速率 kg/s
cp - 泄漏液体的定压热容 kJ/(kg.K)
tt - 储存温度，单位为K
tb - 泄漏液体的沸点，单位为K
hv - 泄漏液体的蒸发热，单位为J/kg
a1 - 液池面积 m2
t0 - 环境温度 K
h - 液体蒸发热 J/kg
t - 蒸发时间 s
u - 风速 m/s
r - 液池半径 m
m - 泄漏物质分子量
t1 - 闪蒸蒸发时间 单位为s 为NUll时不计入总气云质量
t2 - 热量蒸发时间，单位为s 为NUll时不计入总气云质量
t3 - 从液体泄漏 到液体全部处理完毕的时间，单位为s 为NUll时不计入总气云质量
q - 气云燃烧热 J.kg-1
lng - 起火中心点经度
lat - 起火中心点纬度
height - 起火中心点高度
distance - 扩散距离
extendDistance - 子扩散距离用于渲染
返回:
空间点中无约束蒸气云爆炸冲击波正相最大超压 Kpa
```

#### 高斯烟团计算模型 gaussSmokeRegiment

```

public java.util.Set<SpacePoint> gaussSmokeRegiment(double ws,
                                                    double t,
                                                    double q,
                                                    double angle,
                                                    double lng,
                                                    double lat,
                                                    double height,
                                                    double distance,
                                                    double step)
高斯烟团计算模型
参数:
ws - 平均风速 m/s
t - 扩散时间 s
q - 瞬时排放的物料质量 kg
angle - 风向 度
lng - 起火中心点经度
lat - 起火中心点纬度
height - 起火中心点高度
distance - 扩散距离
step - 步长
返回:
空间点中气体浓度
```

#### 带入扩散系数计算高斯烟羽模型 gaussPlumeWithFactor

```

public java.util.Set<SpacePoint> gaussPlumeWithFactor(double ws,
                                                      double q,
                                                      java.lang.Integer l,
                                                      double angle,
                                                      double h,
                                                      double lng,
                                                      double lat,
                                                      double height,
                                                      double distance,
                                                      double step)
带入扩散系数计算高斯烟羽模型
参数:
ws - 平均风速 m/s 只考虑横向风
q - 连续泄露的质量流量 kg/s
l - 太阳辐射等级
h - 泄漏源源高
angle - 风向角度
lng - 起火中心点经度
lat - 起火中心点纬度
height - 起火中心点高度
distance - 扩散距离
step - 步长
返回:
空间点中气体浓度
```

#### 不带入扩散系数计算高斯烟羽模型 gaussPlumeWithoutFactor

```

public java.util.Set<SpacePoint> gaussPlumeWithoutFactor(double q,
                                                         double u,
                                                         double angle,
                                                         double h,
                                                         double lng,
                                                         double lat,
                                                         double distance,
                                                         double step)
不带入扩散系数计算高斯烟羽模型
参数:
q - 物料连续泄漏的质量流量，单位kg/s
u - 平均风速m/s
h - 泄露源源高
angle - 风向角度
lng - 起火中心点经度
lat - 起火中心点纬度
distance - 扩散距离
step - 步长
返回:
空间点中气体浓度
```

#### 不带入扩散系数计算高斯烟羽模型 gaussPlumeWithoutFactor

```

public java.util.List<java.util.Map<java.lang.String,java.lang.Object>> gaussPlumeWithoutFactor(java.lang.Double q,
                                                                                                java.lang.Double ce,
                                                                                                java.lang.Double se,
                                                                                                java.lang.Double h,
                                                                                                java.lang.Double[] ue,
                                                                                                java.lang.Integer step,
                                                                                                java.lang.Double z)
不带入扩散系数计算高斯烟羽模型
参数:
q - 物料连续泄漏的质量流量，单位kg/s
ce - 平均风速m/s
h - 泄露源源高
se - 风向角度
ue - 扩散原点偏移后经纬度坐标
step - 步长
z - 水平高度
返回:
空间点中气体浓度
```

#### 烟羽扩散 calSpread

```

public java.lang.Integer calSpread(double q,
                                   java.lang.Double[] ue,
                                   java.lang.Double[] originMercator,
                                   double x,
                                   double y,
                                   double ce,
                                   java.lang.Double se,
                                   int s,
                                   double h)
参数:
q - 物料连续泄漏的质量流量，单位kg/s
ue - 扩散原点经纬度坐标
originMercator - 扩散原点墨卡托坐标
x - x方向增值
y - y方向增值
ce - 风速
se - 风向角
s - 時間
```

#### 烟羽扩散 calculate2

```

public java.lang.Integer calculate2(java.lang.Double Q,
                                    java.lang.Double x,
                                    java.lang.Double y,
                                    java.lang.Double z,
                                    java.lang.Double h,
                                    java.lang.Double u,
                                    java.lang.Double t)
烟羽扩散
参数:
Q - 气载污染物源强，即释放率（mg/s）
x - 下风向距离（m）
y - 横截风向距离（m）
z - 距水平的高度（m）
h - 排口高度
u - 平均风速m/s
t - 时间s
返回:
等级
```

#### 不带入扩散系数计算高斯烟羽模型 calLevel

```

public int calLevel(double aa)
计算因子level
参数:
aa - 因子浓度值
返回:
gaussPlumeWithoutFactorInCesium
public java.util.Set<java.util.Map<java.lang.String,java.lang.Object>> gaussPlumeWithoutFactorInCesium(double q,
                                                                                                       double u,
                                                                                                       double angle,
                                                                                                       double h,
                                                                                                       double lng,
                                                                                                       double lat,
                                                                                                       int t,
                                                                                                       double xStep,
                                                                                                       double yStep,
                                                                                                       double zStep,
                                                                                                       double xLimit,
                                                                                                       double yLimit,
                                                                                                       double zLimit)
不带入扩散系数计算高斯烟羽模型
参数:
q - 物料连续泄漏的质量流量，单位kg/s
u - 平均风速m/s
h - 泄露源源高
angle - 风向角度
lng - 起火中心点经度
lat - 起火中心点纬度
返回:
空间点中气体浓度

```

### 污染因子操作工具 PollutantUtil

#### 获取气体污染因子的信息 gasInfo

```

public PollutionGas gasInfo(java.lang.String gasCode)
获取气体污染因子的信息
参数:
gasCode - 气体污染因子编码
返回:
该废气的详细信息
```

#### 获取水体污染因子信息 waterInfo

```

public PollutionWater waterInfo(java.lang.String waterCode)
获取水体污染因子信息
参数:
waterCode - 废水污染因子编码
返回:
该废水的详细信息
```

#### hj2005国标转为hj2017国标 oldCodeCast

```

public java.lang.String oldCodeCast(java.lang.String oldCode)
hj2005国标转为hj2017国标
参数:
oldCode - 旧国标编码hj212-2005
返回:
新国标编码hj212-2017
```

### 空间几何工具类  SpaceGeometryUtil

#### 计算立方体顶点坐标 cubePeak

```

public java.util.Set<Point3D> cubePeak(Point3D point3D,
                                       double distance)
计算立方体顶点坐标
参数:
point3D - 立方体中心点的坐标
distance - 立方体边长/2
返回:
立方体8个顶点坐标
```

#### 计算立方体6个面 cubeFace

```

public java.util.Set<java.util.Set<Point3D>> cubeFace(Point3D point3D,
                                                      double distance)
计算立方体6个面
参数:
point3D - 立方体中心点坐标
distance - 立方体的边长/2
返回:
立方体的8个面
```

#### 计算空间立方体延伸距离保证数据充实 batchPeak

```

public java.util.Set<Point3D> batchPeak(Point3D point3D,
                                        double distance,
                                        double extendDis)
计算空间立方体延伸距离保证数据充实
参数:
point3D - 立方体中心点坐标
distance - 立方体的边长/2
extendDis - 延伸的距离
返回:
空间立方体延伸距离
```

#### earthCubePeak

```

public java.util.Set<Point3D> earthCubePeak(Point3D point3D,
                                            double distance)
计算地球中立方体顶点坐标
参数:
point3D - 立方体中心点的坐标
distance - 立方体边长/2
返回:
立方体8个顶点坐标
```

#### 计算地球中立方体顶点坐标 earthBatchPeak

```

public java.util.Set<Point3D> earthBatchPeak(Point3D point3D,
                                             double distance,
                                             double extendDis)
计算地球中立方体顶点坐标
参数:
point3D - 立方体中心点的坐标
distance - 立方体边长/2
extendDis - 辅助计算空间点的距离
返回:
地球中立方体8个顶点坐标
```

#### 包含风向计算地球中立方体顶点坐标 earthCubePeakDetail

```

public java.util.Set<EarthPoint3D> earthCubePeakDetail(Point3D point3D,
                                                       double distance,
                                                       double angle)
包含风向计算地球中立方体顶点坐标
参数:
point3D - 立方体中心点的坐标
distance - 立方体边长/2
angle - 风向角度
返回:
立方体8个顶点坐标
```

#### 计算地球中立方体顶点坐标 earthBatchPeakDetail

```

public java.util.Set<EarthPoint3D> earthBatchPeakDetail(Point3D point3D,
                                                        double distance,
                                                        double extendDis,
                                                        double angle)
计算地球中立方体顶点坐标
参数:
point3D - 立方体中心点的坐标
distance - 立方体边长/2
extendDis - 辅助距离使数据充实
angle - 风向角度
返回:
地球中立方体8个顶点坐标
```

#### 计算地球中立方体顶点坐标 earthBatchPeakDetail

```

public java.util.Set<EarthPoint3D> earthBatchPeakDetail(Point3D point3D,
                                                        double distance,
                                                        double extendDis)
计算地球中立方体顶点坐标
参数:
point3D - 立方体中心点的坐标
distance - 立方体边长/2
extendDis - 辅助距离使数据充实
返回:
地球中立方体8个顶点坐标
```

#### 包含风向计算地球中立方体顶点坐标（顺风向） EarthBatchPeakDetailToWd

```

public java.util.Set<EarthPoint3D> EarthBatchPeakDetailToWd(Point3D point3D,
                                                            double distance,
                                                            double angle,
                                                            double step)
包含风向计算地球中立方体顶点坐标（顺风向）
参数:
point3D - 立方体中心点的坐标
distance - 立方体边长/2
angle - 风向角度
step - 平均风的步长
返回:
立方体多个顶点坐标
```

#### 不包含风向计算地球中立方体顶点坐标 totalEarthBatchPeakDetailWithoutWd

```

public java.util.Set<EarthPoint3D> totalEarthBatchPeakDetailWithoutWd(Point3D point3D,
                                                                      double distance,
                                                                      double angle,
                                                                      double step)
不包含风向计算地球中立方体顶点坐标
参数:
point3D - 立方体中心点的坐标
distance - 立方体边长/2
angle - 风向角度
step - 平均风的步长
返回:
立方体多个顶点坐标
```

#### 计算一个中心点之外所有点位 takeAllPoints

```

public java.util.Set<Point3D> takeAllPoints(double xStep,
                                            double yStep,
                                            double zStep,
                                            double xLimit,
                                            double yLimit,
                                            double zLimit,
                                            boolean xCount,
                                            boolean yCount,
                                            boolean zCount)
计算一个中心点之外所有点位
参数:
xStep - x方向步长
yStep - y方向步长
zStep - z方向步长
xLimit - x向外延申得距离
yLimit - y向外延申得距离
zLimit - z向外延申得距离
xCount - 是否计算x负方向
yCount - 是否计算y负方向
zCount - 是否计算z负方向
返回:
中心点外所有点位列表
```

### 池火灾模型计算 PoolFireUtil

#### 池火灾模型计算 mudan

```

public java.lang.Double mudan(double s,
                              double h,
                              double x,
                              double n,
                              double m,
                              double d,
                              double hc)
池火灾模型计算
参数:
s - 观察者距液池中心的距离与火焰半径的比
h - 火焰高度与火焰半径的比
x - 目标储罐离液池中心的水平距离
n - 效率因子
m - 单位池面积质量燃烧率 kg/(m2·s)
d - 液池直径
hc - 液体燃烧热，kJ/kg
返回:
火焰表面平均辐射强度 kW/m2
```

#### 燃烧速度 mf

```

public java.lang.Double mf(double hc,
                           double cp,
                           double tb,
                           double t0,
                           double h)
燃烧速度
参数:
hc - 液体燃烧热 J/Kg
cp - 液体比压定热容 J/(kg·K)
tb - 液体沸点 K
t0 - 环境温度 K
h - 液体的汽化热 J/Kg
返回:
单位池面积燃烧率 kg/(m2·s)
```

#### 无风时火焰高度 fireHeightWithoutWind

```

public java.lang.Double fireHeightWithoutWind(double m,
                                              double p0,
                                              double g,
                                              double d)
无风时火焰高度
参数:
m - 单位池面积质量燃烧率 kg/(m2·s)
p0 - 空气密度，kg/m3
g - 重力加速度，取9.8m/s2
d - 液池直径，m
返回:
火焰高度 m
```

#### 有风时火焰高度 fireHeightWithWind

```

public java.lang.Double fireHeightWithWind(double m,
                                           double p0,
                                           double g,
                                           double d,
                                           double u,
                                           double pv)
有风时火焰高度
参数:
m - 单位池面积质量燃烧率 kg/(m2·s)
p0 - 空气密度，kg/m3
g - 重力加速度，取9.8m/s2
d - 液池直径，m
u - 10米高处的风速，m/s
pv - 可燃液体的蒸气密度，kg/m3
返回:
火焰高度 m
```

#### 无风时火焰表面平均辐射强度 countWithoutWind

```

public java.lang.Double countWithoutWind(double n,
                                         double hc,
                                         double x,
                                         double m,
                                         double p0,
                                         double g,
                                         double d)
无风时火焰表面平均辐射强度
参数:
n - 效率因子 取值为0.13-0.35
hc - 液体燃烧热，kJ/kg
x - 目标储罐离液池中心的水平距离
m - 单位池面积质量燃烧率 kg/(m2·s)
p0 - 空气密度，kg/m3
g - 重力加速度，取9.8m/s2
d - 液池直径，m
返回:
火焰表面平均辐射强度 kW/m2
```

#### 有风时火焰表面平均辐射强度 countWithWind

```

public java.lang.Double countWithWind(double n,
                                      double hc,
                                      double x,
                                      double m,
                                      double p0,
                                      double g,
                                      double d,
                                      double u,
                                      double pv)
有风时火焰表面平均辐射强度
参数:
n - 效率因子
hc - 液体燃烧热，kJ/kg
x - 目标储罐离液池中心的水平距离
m - 位池面积质量燃烧率 kg/(m2·s)
p0 - 空气密度，kg/m3
g - 重力加速度，取9.8m/s2
d - 液池直径，m
u - 10米高处的风速，m/s
pv - 可燃液体的蒸气密度，kg/m3
返回:
火焰表面平均辐射强度 kW/m2
```

#### 热辐射通量 thermalRadiationFlux

```

public java.lang.Double thermalRadiationFlux(double d,
                                             double h,
                                             double v,
                                             double n,
                                             double hc)
热辐射通量
参数:
d - 液池直径 m
h - 火焰高度 m
v - 燃烧速度 kg/(m2·s)
n - 效率因子 取值为0.13-0.35
hc - 液体燃烧热，kJ/kg
返回:
液池燃烧时放出的总热辐射通量 W
```

#### 入射热辐射强度 heatRadiationIntensity

```

public java.lang.Double heatRadiationIntensity(double q,
                                               double tc,
                                               double x)
入射热辐射强度
参数:
q - 热辐射通量 W
tc - 热传导系数，在无相对理想的数据时，可取值为1
x - 目标点到液池中心距离
返回:
入射通量 W/m2
```

#### 无风时火焰表面平均辐射强度 count

```

public java.lang.Double count(double n,
                              double hc,
                              double x,
                              double m,
                              double p0,
                              double g,
                              double d)
无风时火焰表面平均辐射强度
参数:
n - 效率因子 取值为0.13-0.35
hc - 液体燃烧热，kJ/kg
x - 目标储罐离液池中心的水平距离
m - 单位池面积质量燃烧率 kg/(m2·s)
p0 - 空气密度，kg/m3
g - 重力加速度，取9.8m/s2
d - 液池直径，m
返回:
火焰表面平均辐射强度 kW/m2
```

#### 根据热射通量计算距离 x

```

public java.lang.Double x(double q,
                          double tc,
                          double i)
根据热射通量计算距离
参数:
q - 总热辐射通量 KW
tc - 热传导系数，在无相对理想的数据时，可取值为1
i - 入射通量 kW/m2
返回:
距离 m
```

### 将当前时间转换为短字符的uuid UuidFormByTimeUtil

#### 时间转换为更短的唯一标识符 uuid

```

public java.lang.String uuid(java.lang.String headPrefix,
                             @NotNull
                             @NotNull java.lang.String middlePrefix)
时间转换为更短的唯一标识符
参数:
headPrefix - 首位前缀
middlePrefix - 标识符
返回:
唯一标识符
```

#### 时间转换为更短的唯一标识符 uuid

```

public java.lang.String uuid(int startYear,
                             java.lang.String headPrefix,
                             java.lang.String middlePrefix)
时间转换为更短的唯一标识符
参数:
startYear - 起始年份
headPrefix - 首位前缀
middlePrefix - 标识符
返回:
唯一标识符
```

### 蒸汽云爆炸模型 VaporCloudExplosionUtil

#### 无约束蒸气云爆炸冲击波 count

```
public java.lang.Double count(double w,
                              double q,
                              double d)
无约束蒸气云爆炸冲击波
参数:
w - 泄漏物料形成的气云质量 kg
q - 气云燃烧热 J.kg-1
d - 蒸气云中心距离 m
返回:
无约束蒸气云爆炸冲击波正相最大超压 Kpa
```

### 容器爆炸模型 VesselExplosionUtil

#### 冲击波超压值 count

```
public java.lang.Double count(double p,
                              double v,
                              double r)
冲击波超压值
参数:
p - 容器内气体的绝对压力（MPa）
v - V为容器的容积（m3）
r - 爆炸点距防护目标的距离（m）
返回:
目标位置冲击波超压值 kPa
```

### 水质等级计算 WaterGradeUtil

#### 根据因子浓度值计算水质等级 浓度值单位为HJ212中缺省计量单位, 默认不为湖泊、水库中水质 countWaterGrade

```
public java.lang.Integer countWaterGrade(java.util.Map<java.lang.String,java.lang.Double> pollutantMap)
根据因子浓度值计算水质等级 浓度值单位为HJ212中缺省计量单位, 默认不为湖泊、水库中水质
参数:
pollutantMap - 以HJ212因子编码为key 浓度值为value的map
返回:
水质等级
```
#### 根据因子浓度值计算水质等级 浓度值单位为HJ212中缺省计量单位 countWaterGrade

```
public java.lang.Integer countWaterGrade(java.util.Map<java.lang.String,java.lang.Double> pollutantMap,
                                         boolean isLakeOrReservoir)
根据因子浓度值计算水质等级 浓度值单位为HJ212中缺省计量单位
参数:
pollutantMap - 以HJ212因子编码为key 浓度值为value的map
isLakeOrReservoir - 是否检测水库、湖泊中的水质等级
返回:
水质等级
```

### WGS84坐标系与墨卡托坐标系转换 WGS84MercatorToLngLatUtil

#### 墨卡托转WGS84 mercatorToWgs84

```

public EarthPoint2D mercatorToWgs84(Point2D point2D)
墨卡托转WGS84
参数:
point2D - 墨卡托坐标
返回:
WGS84坐标
```

#### WGS84转墨卡托坐标 wgs84ToMercator

```

public Point2D wgs84ToMercator(EarthPoint2D earthPoint2D)
WGS84转墨卡托坐标
参数:
earthPoint2D - WGS84坐标
返回:
墨卡托坐标
```

#### 经纬度转平面坐标（墨卡托投影） lonLatToMercator

```

public static java.lang.Double[] lonLatToMercator(java.lang.Double lat,
                                                  java.lang.Double lng)
经纬度转平面坐标（墨卡托投影）
参数:
lat - 维度
lng - 经度
返回:
点位
```

#### 墨卡托转经纬度 mercatorToLonLat

```

public static java.lang.Double[] mercatorToLonLat(java.lang.Double X,
                                                  java.lang.Double Y)
墨卡托转经纬度
参数:
X - x
Y - y
返回:
经纬度数组
```
