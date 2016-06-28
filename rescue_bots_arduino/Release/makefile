################################################################################
# Automatically-generated file. Do not edit!
################################################################################

-include ../makefile.init

RM := rm -f

# All of the sources participating in the build are defined here
-include sources.mk
-include subdir.mk
-include core/core/umm_malloc/subdir.mk
-include core/core/spiffs/subdir.mk
-include core/core/libb64/subdir.mk
-include core/core/subdir.mk
-include objects.mk

ifneq ($(MAKECMDGOALS),clean)
ifneq ($(strip $(CC_DEPS)),)
-include $(CC_DEPS)
endif
ifneq ($(strip $(C++_DEPS)),)
-include $(C++_DEPS)
endif
ifneq ($(strip $(C_UPPER_DEPS)),)
-include $(C_UPPER_DEPS)
endif
ifneq ($(strip $(CXX_DEPS)),)
-include $(CXX_DEPS)
endif
ifneq ($(strip $(ASM_DEPS)),)
-include $(ASM_DEPS)
endif
ifneq ($(strip $(INO_DEPS)),)
-include $(INO_DEPS)
endif
ifneq ($(strip $(PDE_DEPS)),)
-include $(PDE_DEPS)
endif
ifneq ($(strip $(S_UPPER_DEPS)),)
-include $(S_UPPER_DEPS)
endif
ifneq ($(strip $(CPP_DEPS)),)
-include $(CPP_DEPS)
endif
ifneq ($(strip $(C_DEPS)),)
-include $(C_DEPS)
endif
endif

-include ../makefile.defs

# Add inputs and outputs from these tool invocations to the build variables 
AR += \
arduino.ar \

ELF += \
rescue_bots_arduino.elf \

EEP += \
rescue_bots_arduino.eep \

FLASH_IMAGE += \
rescue_bots_arduino.bin \


# All Target
all: rescue_bots_arduino

# Tool invocations
rescue_bots_arduino: $(FLASH_IMAGE)
	@echo 'Building target: $@'
	@echo 'Printing size:'
	"/Applications/sloeber.app/Contents/Eclipse/arduinoPlugin/tools/esp8266/xtensa-lx106-elf-gcc/1.20.0-26-gb404fb9-2/bin/xtensa-lx106-elf-size" -A "/Users/nova3d-macmini03/Dropbox/Nova3d/rescue_bots_v2/workspace/rescue_bots_arduino/Release/rescue_bots_arduino.elf"
	@echo 'Finished building target: $@'
	@echo ' '

arduino.ar: $(AR_OBJ)
	@echo 'Starting archiver'
	"/Applications/sloeber.app/Contents/Eclipse/arduinoPlugin/tools/esp8266/xtensa-lx106-elf-gcc/1.20.0-26-gb404fb9-2/bin/xtensa-lx106-elf-ar" cru  "/Users/nova3d-macmini03/Dropbox/Nova3d/rescue_bots_v2/workspace/rescue_bots_arduino/Release/arduino.ar"    arduino.ar $(AR_OBJ)
	@echo 'Finished building: $@'
	@echo ' '

rescue_bots_arduino.elf: $(LINK_OBJ) $(AR)
	@echo 'Starting combiner'
	"/Applications/sloeber.app/Contents/Eclipse/arduinoPlugin/tools/esp8266/xtensa-lx106-elf-gcc/1.20.0-26-gb404fb9-2/bin/xtensa-lx106-elf-gcc" -g -Os -nostdlib -Wl,--no-check-sections -u call_user_start -Wl,-static "-L/Applications/sloeber.app/Contents/Eclipse/arduinoPlugin/packages/esp8266/hardware/esp8266/2.2.0/tools/sdk/lib" "-L/Applications/sloeber.app/Contents/Eclipse/arduinoPlugin/packages/esp8266/hardware/esp8266/2.2.0/tools/sdk/ld" "-Teagle.flash.512k64.ld" -Wl,--gc-sections -Wl,-wrap,system_restart_local -Wl,-wrap,register_chipv6_phy  -o "/Users/nova3d-macmini03/Dropbox/Nova3d/rescue_bots_v2/workspace/rescue_bots_arduino/Release/rescue_bots_arduino.elf" -Wl,--start-group   $(LINK_OBJ) $(AR)  "/Users/nova3d-macmini03/Dropbox/Nova3d/rescue_bots_v2/workspace/rescue_bots_arduino/Release/arduino.ar" -lm -lgcc -lhal -lphy -lpp -lnet80211 -lwpa -lcrypto -lmain -lwps -laxtls -lsmartconfig -lmesh -lwpa2 -llwip -Wl,--end-group  "-L/Users/nova3d-macmini03/Dropbox/Nova3d/rescue_bots_v2/workspace/rescue_bots_arduino/Release"
	@echo 'Finished building: $@'
	@echo ' '

rescue_bots_arduino.eep: $(ELF)
	@echo 'Create eeprom image'
	${A.RECIPE.OBJCOPY.EEP.PATTERN}
	@echo 'Finished building: $@'
	@echo ' '

rescue_bots_arduino.bin: $(EEP)
	@echo 'Create Flash image (ihex format)'
	"/Applications/sloeber.app/Contents/Eclipse/arduinoPlugin/tools/esp8266/esptool/0.4.8/esptool" -eo "/Applications/sloeber.app/Contents/Eclipse/arduinoPlugin/packages/esp8266/hardware/esp8266/2.2.0/bootloaders/eboot/eboot.elf" -bo "/Users/nova3d-macmini03/Dropbox/Nova3d/rescue_bots_v2/workspace/rescue_bots_arduino/Release/rescue_bots_arduino.bin" -bm qio -bf 40 -bz 512K -bs .text -bp 4096 -ec -eo "/Users/nova3d-macmini03/Dropbox/Nova3d/rescue_bots_v2/workspace/rescue_bots_arduino/Release/rescue_bots_arduino.elf" -bs .irom0.text -bs .text -bs .data -bs .rodata -bc -ec
	@echo 'Finished building: $@'
	@echo ' '

# Other Targets
clean:
	-$(RM) $(C_UPPER_DEPS)$(PDE_DEPS)$(C_DEPS)$(AR)$(EEP)$(FLASH_IMAGE)$(CC_DEPS)$(AR_OBJ)$(C++_DEPS)$(LINK_OBJ)$(CXX_DEPS)$(ASM_DEPS)$(INO_DEPS)$(SIZEDUMMY)$(S_UPPER_DEPS)$(ELF)$(CPP_DEPS) rescue_bots_arduino
	-@echo ' '

.PHONY: all clean dependents
.SECONDARY:

-include ../makefile.targets
