#include <finnstr.libgdx.liquidfun.ParticleSystem.h>

//@line:17

#include <Box2D/Box2D.h>
	JNIEXPORT jlong JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniCreateParticleSystem(JNIEnv* env, jobject object, jlong worldAddr, jfloat radius, jfloat pressureStrength, jfloat dampingStrength, jfloat elasticStrength, jfloat springStrength, jfloat viscousStrength, jfloat surfaceTensionPressureStrength, jfloat surfaceTensionNormalStrength, jfloat repulsiveStrength, jfloat powderStrength, jfloat ejectionStrength, jfloat staticPressureStrength, jfloat staticPressureRelaxation, jint staticPressureIterations, jfloat colorMixingStrength, jboolean destroyByAge, jfloat lifetimeGranularity, jboolean strictContactCheck, jfloat density, jfloat gravityScale, jint maxCount) {


//@line:51

		b2ParticleSystemDef def;
		def.radius = radius;
		def.pressureStrength = pressureStrength;
		def.dampingStrength = dampingStrength;
		def.elasticStrength = elasticStrength;
		def.springStrength = springStrength;
		def.viscousStrength = viscousStrength;
		def.surfaceTensionPressureStrength = surfaceTensionPressureStrength;
		def.surfaceTensionNormalStrength = surfaceTensionNormalStrength;
		def.repulsiveStrength = repulsiveStrength;
		def.powderStrength = powderStrength;
		def.ejectionStrength = ejectionStrength;
		def.staticPressureStrength = staticPressureStrength;
		def.staticPressureRelaxation = staticPressureRelaxation;
		def.staticPressureIterations = staticPressureIterations;
		def.colorMixingStrength = colorMixingStrength;
		def.destroyByAge = destroyByAge;
		def.lifetimeGranularity = lifetimeGranularity;
		def.strictContactCheck = strictContactCheck;
		def.density = density;
		def.gravityScale = gravityScale;
		def.maxCount = maxCount;
		
		b2World* world = (b2World*)worldAddr;
		return (jlong)world->CreateParticleSystem(&def);
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniDestroyParticleSystem(JNIEnv* env, jobject object, jlong worldAddr, jlong systemAddr) {


//@line:83

		b2World* world = (b2World*)worldAddr;
		b2ParticleSystem* system = (b2ParticleSystem*)systemAddr;
		
		world->DestroyParticleSystem(system);
	

}

JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniCreateParticle(JNIEnv* env, jobject object, jlong addr, jint pFlags, jfloat pPositionX, jfloat pPositionY, jfloat pVelocityX, jfloat pVelocityY, jint pColorR, jint pColorG, jint pColorB, jint pColorA, jfloat lifetime, jboolean addToGroup, jlong groupAddr) {


//@line:110

		b2ParticleDef particleDef;
		particleDef.flags = pFlags;
		particleDef.position.Set( pPositionX, pPositionY );
		particleDef.velocity.Set( pVelocityX, pVelocityY );
		particleDef.color.Set(pColorR, pColorG, pColorB, pColorA);
		particleDef.lifetime = lifetime;
		if(addToGroup) particleDef.group = (b2ParticleGroup*)groupAddr;
		
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 index = system->CreateParticle( particleDef );
		return (jint)index;
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniDestroyParticle(JNIEnv* env, jobject object, jlong addr, jint pIndex) {


//@line:130

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->DestroyParticle( pIndex, false );
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniDestroyOldestParticle(JNIEnv* env, jobject object, jlong addr, jint index) {


//@line:144

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->DestroyOldestParticle(index, false);
	

}

JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniDestroyParticleInShape(JNIEnv* env, jobject object, jlong addr, jlong pShapeAddr, jfloat pTransformPosX, jfloat pTransformPosY, jfloat pAngle) {


//@line:157

		b2Shape* shape = (b2Shape*)pShapeAddr;
		b2Transform transform;
		transform.Set( b2Vec2( pTransformPosX, pTransformPosY ), pAngle );
		
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jint)system->DestroyParticlesInShape( *shape, transform, false );
	

}

JNIEXPORT jlong JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniCreateParticleGroup(JNIEnv* env, jobject object, jlong addr, jint pFlags, jint pGroupFlags, jfloat pPositionX, jfloat pPositionY, jfloat pAngle, jfloat pLinVelocityX, jfloat pLinVelocityY, jfloat pAngularVelocity, jint pColorR, jint pColorG, jint pColorB, jint pColorA, jfloat pStrength, jlong pShapeAddr, jfloat stride, jint particleCount, jboolean positionDataSet, jfloat positionDataX, jfloat positionDataY, jfloat lifetime, jlong groupAddr) {


//@line:210

		b2ParticleGroupDef groupDef;
		groupDef.flags = pFlags;
		groupDef.groupFlags = pGroupFlags;
		groupDef.position.Set( pPositionX, pPositionY );
		groupDef.angle = pAngle;
		groupDef.linearVelocity.Set( pLinVelocityX, pLinVelocityY );
		groupDef.angularVelocity = pAngularVelocity;
		groupDef.color.Set( pColorR, pColorG, pColorB, pColorA );
		groupDef.strength = pStrength;
		groupDef.shape = (b2Shape*)pShapeAddr;
		groupDef.stride = stride;
		groupDef.particleCount = particleCount;
		groupDef.lifetime = lifetime;
		
		if(positionDataSet) {
			const b2Vec2 positionData = b2Vec2(positionDataX, positionDataY);
			groupDef.positionData = &positionData;
		}
		
		if(groupAddr != -1) {
			groupDef.group = (b2ParticleGroup*)groupAddr;
		}
		
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jlong)system->CreateParticleGroup( groupDef );
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniJoinParticleGroups(JNIEnv* env, jobject object, jlong addr, jlong addrA, jlong addrB) {


//@line:245

		b2ParticleGroup* groupA = (b2ParticleGroup*)addrA;
		b2ParticleGroup* groupB = (b2ParticleGroup*)addrB;
	
		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->JoinParticleGroups( groupA, groupB );
	

}

JNIEXPORT jfloatArray JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticlePositionBufferX(JNIEnv* env, jobject object, jlong addr) {


//@line:269

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jfloatArray array;
		array = env->NewFloatArray((jsize) count);
		
		jfloat fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetPositionBuffer()[i].x;
		}
		
		env->SetFloatArrayRegion(array, 0, (jsize) count, fill);
 		return array;
	

}

JNIEXPORT jfloatArray JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticlePositionBufferY(JNIEnv* env, jobject object, jlong addr) {


//@line:284

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count =  system->GetParticleCount();
		
		jfloatArray array;
		array = env->NewFloatArray((jsize) count);
		
		jfloat fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetPositionBuffer()[i].y;
		}
		
		env->SetFloatArrayRegion(array, 0, (jsize) count, fill);
		return array;
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniUpdateParticlePositionBuffer(JNIEnv* env, jobject object, jlong addr, jfloatArray obj_buffer) {
	float* buffer = (float*)env->GetPrimitiveArrayCritical(obj_buffer, 0);


//@line:314

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		for(int i = 0; i < count * 2; i += 2) {
			buffer[i] = system->GetPositionBuffer()[i / 2].x;
			buffer[i + 1] = system->GetPositionBuffer()[i / 2].y;
		}
	
	env->ReleasePrimitiveArrayCritical(obj_buffer, buffer, 0);

}

JNIEXPORT jlong JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticlePositionBufferAddress(JNIEnv* env, jobject object, jlong addr) {


//@line:328

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jlong) &system->GetPositionBuffer()->x;
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniUpdateParticlePositionAndColorBuffer(JNIEnv* env, jobject object, jlong addr, jfloatArray obj_buffer) {
	float* buffer = (float*)env->GetPrimitiveArrayCritical(obj_buffer, 0);


//@line:347

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		for(int i = 0; i < count; i++) {
			buffer[i * 6] = system->GetPositionBuffer()[i].x;
			buffer[i * 6 + 1] = system->GetPositionBuffer()[i].y;
			buffer[i * 6 + 2] = system->GetColorBuffer()[i].r / 255.0;
			buffer[i * 6 + 3] = system->GetColorBuffer()[i].g / 255.0;
			buffer[i * 6 + 4] = system->GetColorBuffer()[i].b / 255.0;
			buffer[i * 6 + 5] = system->GetColorBuffer()[i].a / 255.0;
		}
	
	env->ReleasePrimitiveArrayCritical(obj_buffer, buffer, 0);

}

JNIEXPORT jfloatArray JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleVelocityBufferX(JNIEnv* env, jobject object, jlong addr) {


//@line:369

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jfloatArray array;
		array = env->NewFloatArray((jsize) count);
		
		jfloat fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetVelocityBuffer()[i].x;
		}
		
		env->SetFloatArrayRegion(array, 0, (jsize) count, fill);
 		return array;
	

}

JNIEXPORT jfloatArray JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleVelocityBufferY(JNIEnv* env, jobject object, jlong addr) {


//@line:384

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jfloatArray array;
		array = env->NewFloatArray((jsize) count);
		
		jfloat fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetVelocityBuffer()[i].y;
		}
		
		env->SetFloatArrayRegion(array, 0, (jsize) count, fill);
		return array;
	

}

JNIEXPORT jintArray JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleColorBufferR(JNIEnv* env, jobject object, jlong addr) {


//@line:408

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jintArray array;
		array = env->NewIntArray((jsize) count);
		
		jint fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetColorBuffer()[i].r;
		}
		
		env->SetIntArrayRegion(array, 0, (jsize) count, fill);
		return array;
	

}

JNIEXPORT jintArray JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleColorBufferG(JNIEnv* env, jobject object, jlong addr) {


//@line:424

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jintArray array;
		array = env->NewIntArray((jsize) count);
		
		jint fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetColorBuffer()[i].g;
		}
		
		env->SetIntArrayRegion(array, 0, (jsize) count, fill);
		return array;
	

}

JNIEXPORT jintArray JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleColorBufferB(JNIEnv* env, jobject object, jlong addr) {


//@line:440

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jintArray array;
		array = env->NewIntArray((jsize) count);
		
		jint fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetColorBuffer()[i].b;
		}
		
		env->SetIntArrayRegion(array, 0, (jsize) count, fill);
		return array;
	

}

JNIEXPORT jintArray JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleColorBufferA(JNIEnv* env, jobject object, jlong addr) {


//@line:456

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		int32 count = system->GetParticleCount();
		
		jintArray array;
		array = env->NewIntArray((jsize) count);
		
		jint fill[count];
		for(int i = 0; i < count; i++) {
			fill[i] = system->GetColorBuffer()[i].a;
		}
		
		env->SetIntArrayRegion(array, 0, (jsize) count, fill);
		return array;
	

}

JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniCalculateReasonableParticleIterations(JNIEnv* env, jobject object, jlong worldAddr, jfloat timeStep) {


//@line:539

		b2World* world = (b2World*)worldAddr;
		return (jint)world->CalculateReasonableParticleIterations(timeStep);
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniSetParticleRadius(JNIEnv* env, jobject object, jlong addr, jfloat pRadius) {


//@line:548

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetRadius( pRadius );
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleRadius(JNIEnv* env, jobject object, jlong addr) {


//@line:557

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jfloat)system->GetRadius();
	

}

JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleCount(JNIEnv* env, jobject object, jlong addr) {


//@line:567

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jint)system->GetParticleCount();
	

}

JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleGroupCount(JNIEnv* env, jobject object, jlong addr) {


//@line:576

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jint)system->GetParticleGroupCount();
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniSetPaused(JNIEnv* env, jobject object, jlong addr, jboolean paused) {


//@line:589

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetPaused(paused);
	

}

JNIEXPORT jboolean JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetPaused(JNIEnv* env, jobject object, jlong addr) {


//@line:598

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jboolean)system->GetPaused();
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniSetParticleDensity(JNIEnv* env, jobject object, jlong addr, jfloat pDensity) {


//@line:607

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetDensity(pDensity);
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleDensity(JNIEnv* env, jobject object, jlong addr) {


//@line:616

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jfloat)system->GetDensity();
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniSetParticleGravityScale(JNIEnv* env, jobject object, jlong addr, jfloat pGravityScale) {


//@line:625

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetGravityScale(pGravityScale);
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleGravityScale(JNIEnv* env, jobject object, jlong addr) {


//@line:634

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jfloat)system->GetGravityScale();
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniSetParticleMaxCount(JNIEnv* env, jobject object, jlong addr, jfloat pCount) {


//@line:643

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetMaxParticleCount(pCount);
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetMaxParticleCount(JNIEnv* env, jobject object, jlong addr) {


//@line:652

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jint)system->GetMaxParticleCount();
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniSetParticleDamping(JNIEnv* env, jobject object, jlong addr, jfloat pDamping) {


//@line:661

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		system->SetDamping(pDamping);
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetParticleDamping(JNIEnv* env, jobject object, jlong addr) {


//@line:670

		b2ParticleSystem* system = (b2ParticleSystem*)addr;
		return (jfloat)system->GetDamping();
	

}

JNIEXPORT jstring JNICALL Java_finnstr_libgdx_liquidfun_ParticleSystem_jniGetVersionString(JNIEnv* env, jobject object, jlong worldAddr) {


//@line:679

		b2World* world = (b2World*)worldAddr;
		const char* version = world->GetVersionString();
		return env->NewStringUTF(version);
	

}

