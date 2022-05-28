# Fruit_Detection

## Usage:-

- Clone the YOLOv5 repository using the following command.
  
  git clone https://github.com/ultralytics/yolov5 
  
- Open CMD in working directory.
- Run following command.

  
  pip install -r requirements.txt
  
- `detect.py` is the main Python file of fruit detection. 
- `FINAL_YOLO.ipynb` is the Notebook file of the Training our YOLO model.
- Dataset that I have used is [Fruit Dataset](https://data.mendeley.com/datasets/b6fftwbr2v/2).
- Our best model is saved in the `models` file named `best.pt`.
- To use our detection model, run:
```
python detect.py --weights models/best.pt --conf 0.2 --source test_images
