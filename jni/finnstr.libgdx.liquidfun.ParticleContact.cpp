#include <finnstr.libgdx.liquidfun.ParticleContact.h>

//@line:8

#include <Box2D/Box2D.h>
	 JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleContact_jniGetIndexA(JNIEnv* env, jobject object, jlong addr) {


//@line:31

		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetIndexA();
	

}

JNIEXPORT jint JNICALL Java_finnstr_libgdx_liquidfun_ParticleContact_jniGetIndexB(JNIEnv* env, jobject object, jlong addr) {


//@line:41

		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetIndexB();
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleContact_jniGetWeight(JNIEnv* env, jobject object, jlong addr) {


//@line:53

		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetWeight();
	

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleContact_jniGetNormalX(JNIEnv* env, jobject object, jlong addr) {


//@line:66

		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetNormal().x;
	 

}

JNIEXPORT jfloat JNICALL Java_finnstr_libgdx_liquidfun_ParticleContact_jniGetNormalY(JNIEnv* env, jobject object, jlong addr) {


//@line:71

		b2ParticleContact* contact = (b2ParticleContact*)addr;
		return contact->GetNormal().y;
	 

}

