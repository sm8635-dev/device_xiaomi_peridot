#include <stdint.h>
#include <cstddef>

extern "C" {
    void clGetPlatformIDs() {}
    void clGetDeviceIDs() {}
    void clGetDeviceInfo() {}
    void clCreateContext() {}
    void clCreateCommandQueue() {}
    void clCreateProgramWithSource() {}
    void clBuildProgram() {}
    void clGetProgramBuildInfo() {}
    void clCreateKernel() {}
    void clReleaseKernel() {}
    void clReleaseProgram() {}
    void clReleaseCommandQueue() {}
    void clReleaseContext() {}
    void clReleaseDevice() {}
    void clCreateBuffer() {}
    void clEnqueueWriteBuffer() {}
    void clSetKernelArg() {}
    void clEnqueueNDRangeKernel() {}
    void clEnqueueReadBuffer() {}
    void clReleaseMemObject() {}
    void clFinish() {}

    void _ZN12OpenCLHelper6Loader4InitEv() {}
}
