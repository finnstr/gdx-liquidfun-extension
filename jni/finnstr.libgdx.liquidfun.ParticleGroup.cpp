#include <finnstr.libgdx.liquidfun.ParticleGroup.h>

//@line:10

#include <Box2D/Box2D.h>
	JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniDestroyParticlesInGroup(JNIEnv* env, jobject object, jlong addr) {


//@line:26

		b2ParticleGroup* group = (b2ParticleGroup*)addr; 
		group->DestroyParticles();
	

}

JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetBufferIndex(JNIEnv* env, jobject object, jlong addr) {


//@line:43

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jint)group->GetBufferIndex();
	

}

JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetParticleCount(JNIEnv* env, jobject object, jlong addr) {


//@line:52

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jint)group->GetParticleCount();
	

}

JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetGroupFlags(JNIEnv* env, jobject object, jlong addr) {


//@line:61

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jint)group->GetGroupFlags();
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniSetGroupFlags(JNIEnv* env, jobject object, jlong addr, jint pFlags) {


//@line:70

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		group->SetGroupFlags(pFlags);
	 

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetMass(JNIEnv* env, jobject object, jlong addr) {


//@line:79

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetMass();
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetInertia(JNIEnv* env, jobject object, jlong addr) {


//@line:88

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetInertia();
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetCenterX(JNIEnv* env, jobject object, jlong addr) {


//@line:97

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetCenter().x;
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetCenterY(JNIEnv* env, jobject object, jlong addr) {


//@line:101

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetCenter().y;
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetLinVelocityX(JNIEnv* env, jobject object, jlong addr) {


//@line:110

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetLinearVelocity().x;
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetLinVelocityY(JNIEnv* env, jobject object, jlong addr) {


//@line:114

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetLinearVelocity().x;
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetAngularVelocity(JNIEnv* env, jobject object, jlong addr) {


//@line:123

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetAngularVelocity();
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetPositionX(JNIEnv* env, jobject object, jlong addr) {


//@line:132

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetPosition().x;
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetPositionY(JNIEnv* env, jobject object, jlong addr) {


//@line:136

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetPosition().y;
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniGetAngle(JNIEnv* env, jobject object, jlong addr) {


//@line:145

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		return (jfloat)group->GetAngle();
	 

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniApplyForce(JNIEnv* env, jobject object, jlong addr, jfloat forceX, jfloat forceY) {


//@line:154

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		group->ApplyForce(b2Vec2(forceX, forceY));
	

}

JNIEXPORT void JNICALL Java_finnstr_libgdx_liquidfun_ParticleGroup_jniApplyLinearImpulse(JNIEnv* env, jobject object, jlong addr, jfloat impulseX, jfloat impulseY) {


//@line:163

		b2ParticleGroup* group = (b2ParticleGroup*)addr;
		group->ApplyLinearImpulse(b2Vec2(impulseX, impulseY));
	

}

