package com.example.deteksihelmpengendaramotor.ml;

import android.content.Context;
import androidx.annotation.NonNull;
import java.io.IOException;
import java.lang.Integer;
import java.lang.Object;
import java.util.HashMap;
import java.util.Map;
import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.metadata.MetadataExtractor;
import org.tensorflow.lite.support.model.Model;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

/**
 * This model doesn't have metadata, so no javadoc can be generated. */
public final class Yolov4Tiny416 {
  @NonNull
  private final Model model;

  private Yolov4Tiny416(@NonNull Context context, @NonNull Model.Options options) throws
      IOException {
    model = Model.createModel(context, "yolov4-tiny-416.tflite", options);
    MetadataExtractor extractor = new MetadataExtractor(model.getData());
  }

  @NonNull
  public static Yolov4Tiny416 newInstance(@NonNull Context context) throws IOException {
    return new Yolov4Tiny416(context, (new Model.Options.Builder()).build());
  }

  @NonNull
  public static Yolov4Tiny416 newInstance(@NonNull Context context, @NonNull Model.Options options)
      throws IOException {
    return new Yolov4Tiny416(context, options);
  }

  @NonNull
  public Outputs process(@NonNull TensorBuffer inputFeature0) {
    TensorBuffer processedinputFeature0 = inputFeature0;
    Outputs outputs = new Outputs(model);
    model.run(new Object[] {processedinputFeature0.getBuffer()}, outputs.getBuffer());
    return outputs;
  }

  public void close() {
    model.close();
  }

  public class Outputs {
    private TensorBuffer outputFeature0;

    private TensorBuffer outputFeature1;

    private Outputs(Model model) {
      this.outputFeature0 = TensorBuffer.createFixedSize(model.getOutputTensorShape(0), DataType.FLOAT32);
      this.outputFeature1 = TensorBuffer.createFixedSize(model.getOutputTensorShape(1), DataType.FLOAT32);
    }

    @NonNull
    public TensorBuffer getOutputFeature0AsTensorBuffer() {
      return outputFeature0;
    }

    @NonNull
    public TensorBuffer getOutputFeature1AsTensorBuffer() {
      return outputFeature1;
    }

    @NonNull
    private Map<Integer, Object> getBuffer() {
      Map<Integer, Object> outputs = new HashMap<>();
      outputs.put(0, outputFeature0.getBuffer());
      outputs.put(1, outputFeature1.getBuffer());
      return outputs;
    }
  }
}
